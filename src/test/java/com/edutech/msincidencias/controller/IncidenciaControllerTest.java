package com.edutech.msincidencias.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import com.edutech.msincidencias.model.Incidencia;
import com.edutech.msincidencias.repository.IncidenciaRepository;
import com.edutech.msincidencias.service.IncidenciaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.http.MediaType;



@WebMvcTest(IncidenciaController.class)
class IncidenciaControllerTest {
    LocalDate fechaIncidencia = LocalDate.now();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidenciaService incidenciaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Incidencia incidencia;

    @BeforeEach
    void setUp() {
        incidencia = new Incidencia(1L, 10, 5, "titulo", "descripcion", LocalDate.now(), "ABIERTO", "ALTA", true);
    }

    @Test
    public void testGetAllIncidencias() throws Exception {
        when(incidenciaService.findAll()).thenReturn(List.of(incidencia));

        mockMvc.perform(get("/api/v1/incidencias"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("[0].idIncidencia").value(1L));
    }

    @Test
    public void testReadIncidencia() throws Exception {
        when(incidenciaService.findById(incidencia.getIdIncidencia())).thenReturn(incidencia);

        mockMvc.perform(get("/api/v1/incidencias/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idIncidencia").value(1L));
    }

    @Test
    public void testEstado() throws Exception {
        when(incidenciaService.findById(incidencia.getIdIncidencia())).thenReturn(incidencia);

        mockMvc.perform(get("/api/v1/incidencias/estado/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("ABIERTO"));
    }

    @Test
    public void testPostIncidencia() throws Exception {
        when(incidenciaService.save(any(Incidencia.class))).thenReturn(incidencia);
        mockMvc.perform(post("/api/v1/incidencias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incidencia)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idIncidencia").value(1L))
            .andExpect(jsonPath("$.idUsuarioReporte").value(10))
            .andExpect(jsonPath("$.idOperadorAsignado").value(5));
    }

    @Test
    public void testUpdateIncidencia() throws Exception {
        when(incidenciaService.update(eq(1L), any(Incidencia.class))).thenReturn(true);        
        mockMvc.perform(put("/api/v1/incidencias/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incidencia)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idIncidencia").value(1L))
            .andExpect(jsonPath("$.idUsuarioReporte").value(10))
            .andExpect(jsonPath("$.idOperadorAsignado").value(5));
    }

    @Test
    public void testDeleteIncidencia() throws Exception {
        when(incidenciaService.findById(incidencia.getIdIncidencia())).thenReturn(incidencia);
        when(incidenciaService.deleteById(incidencia.getIdIncidencia())).thenReturn(incidencia);

        mockMvc.perform(delete("/api/v1/incidencias/1"))
        .andExpect(status().isNoContent());

    }
}
