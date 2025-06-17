package com.edutech.msincidencias.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.edutech.msincidencias.model.Incidencia;
import com.edutech.msincidencias.repository.IncidenciaRepository;

public class IncidenciaServiceTest {
        @Mock
    private IncidenciaRepository incidenciaRepository;

    @InjectMocks
    private IncidenciaService incidenciaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarIncidencia() {
        LocalDate fechaIncidencia = LocalDate.now();

        Incidencia incidencia = new Incidencia(null, 19, 5, "", "",  fechaIncidencia, "", "");
        Incidencia incidenciaGuardada = new Incidencia(1L, 19, 5, "", "", fechaIncidencia, "", "");
        when(incidenciaRepository.save(incidencia)).thenReturn(incidenciaGuardada);

        Incidencia resultado = incidenciaService.save(incidencia);
        assertThat(resultado.getIdIncidencia()).isEqualTo(1L);
        assertThat(resultado.getIdUsuarioReporte()).isEqualTo(19);
        assertThat(resultado.getIdOperadorAsignado()).isEqualTo(5);
        assertThat(resultado.getTitulo()).isEqualTo("");
        assertThat(resultado.getDescripcion()).isEqualTo("");
        assertThat(resultado.getFechaIncidencia()).isEqualTo(fechaIncidencia);
        assertThat(resultado.getEstado()).isEqualTo("");
        assertThat(resultado.getPrioridad()).isEqualTo("");
        verify(incidenciaRepository).save(incidencia);
    }
}
