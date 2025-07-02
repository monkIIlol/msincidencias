package com.edutech.msincidencias.service;

import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.edutech.msincidencias.model.Incidencia;
import com.edutech.msincidencias.repository.IncidenciaRepository;

public class IncidenciaServiceTest {
    LocalDate fechaIncidencia = LocalDate.now();

    @Mock
    private IncidenciaRepository incidenciaRepository;

    @InjectMocks
    private IncidenciaService incidenciaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarIncidencias() {
        List<Incidencia> listaIncidencias = List.of(
            new Incidencia(154L, 323, 33, "", "",  fechaIncidencia, "", "", true),
            new Incidencia(1L, 19, 5, "", "", fechaIncidencia, "", "", true),
            new Incidencia(14L, 39, 24, "", "", fechaIncidencia, "", "", true),
            new Incidencia(9L, 32, 3, "", "",  fechaIncidencia, "", "", true));
            when(incidenciaRepository.findAll()).thenReturn(listaIncidencias);

            List<Incidencia> resultado = incidenciaService.findAll();
            assertThat(resultado).hasSize(4);
        
            verify(incidenciaRepository).findAll();
    }

    @Test
    void testGuardarIncidencia() {
        Incidencia incidencia = new Incidencia(null, 19, 5, "", "",  fechaIncidencia, "", "", true);
        Incidencia incidenciaGuardada = new Incidencia(1L, 19, 5, "", "", fechaIncidencia, "", "", true);
        when(incidenciaRepository.save(incidencia)).thenReturn(incidenciaGuardada);

        Incidencia resultado = incidenciaService.save(incidencia);
        assertThat(resultado.getIdIncidencia()).isEqualTo(1L);
        /* 
        assertThat(resultado.getIdUsuarioReporte()).isEqualTo(19);
        assertThat(resultado.getIdOperadorAsignado()).isEqualTo(5);
        assertThat(resultado.getTitulo()).isEqualTo("");
        assertThat(resultado.getDescripcion()).isEqualTo("");
        assertThat(resultado.getFechaIncidencia()).isEqualTo(fechaIncidencia);
        assertThat(resultado.getEstado()).isEqualTo("");
        assertThat(resultado.getPrioridad()).isEqualTo("");
        */
        verify(incidenciaRepository).save(incidencia);
    }

    @Test
    void testEncontrarIncidenciaPorId() {
        Incidencia incidenciaEncontrar = new Incidencia(1L, 19, 5, "", "", fechaIncidencia, "", "", true);
        when(incidenciaRepository.findByIdIncidencia(incidenciaEncontrar.getIdIncidencia())).thenReturn(incidenciaEncontrar);

        Incidencia resultado = incidenciaService.findById(incidenciaEncontrar.getIdIncidencia());
        assertThat(resultado).isEqualTo(incidenciaEncontrar);

        verify(incidenciaRepository).findByIdIncidencia(incidenciaEncontrar.getIdIncidencia());
    }

    @Test
    void testEliminarIncidencia() {
        Incidencia incidencia = new Incidencia(9L, 19, 5, "", "",  fechaIncidencia, "", "", true);        
        when(incidenciaRepository.findByIdIncidencia(incidencia.getIdIncidencia())).thenReturn(incidencia);
        when(incidenciaRepository.save(incidencia)).thenReturn(incidencia);

        Incidencia resultado = incidenciaService.deleteById(incidencia.getIdIncidencia());
        assertThat(resultado.isActivo()).isFalse();
        verify(incidenciaRepository).save(resultado);
    }

    @Test
    void testActualizarIncidencia() {
        Incidencia incidenciaExistente = new Incidencia(5L, 19, 5, "", "",  fechaIncidencia, "", "", true);
        Incidencia incidenciaNueva = new Incidencia(5L, 9, 18, "", "", fechaIncidencia, "", "", true);
        when(incidenciaRepository.findByIdIncidencia(incidenciaExistente.getIdIncidencia())).thenReturn(incidenciaNueva);

        boolean resultado = incidenciaService.update(incidenciaNueva.getIdIncidencia(), incidenciaNueva);
        assertThat(resultado).isTrue();

        verify(incidenciaRepository).save(incidenciaNueva);
    }
}
