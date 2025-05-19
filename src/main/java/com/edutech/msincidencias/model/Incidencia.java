package com.edutech.msincidencias.model;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "incidencia")


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int idIncidencia;

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(length = 500, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaIncidencia;

    @Column(length = 50)
    private String estado;

    @Column(length = 50)
    private String prioridad;

}
