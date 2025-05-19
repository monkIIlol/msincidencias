package com.edutech.msincidencias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.msincidencias.repository.IncidenciaRepository;
import com.edutech.msincidencias.model.Incidencia;

@Service
public class IncidenciaService {
    
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    public List<Incidencia> findAll() {
        return incidenciaRepository.findAll();
    }

    public Incidencia save(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    public Incidencia findById(int idIncidencia) {
        return incidenciaRepository.findById(idIncidencia);
    }

}
