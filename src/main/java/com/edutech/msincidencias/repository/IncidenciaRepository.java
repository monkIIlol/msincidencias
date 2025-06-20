package com.edutech.msincidencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.msincidencias.model.Incidencia;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Integer>{
    
    List<Incidencia> findAll();

    @SuppressWarnings("unchecked")
    Incidencia save(Incidencia incidencia);

    Incidencia findById(Long idIncidencia);
}
