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

    public Incidencia findById(Long idIncidencia) {
        return incidenciaRepository.findById(idIncidencia);
    }

    public Incidencia deleteById(Long idIncidencia) {
        Incidencia resultado = incidenciaRepository.findById(idIncidencia);
        resultado.setActivo(false);
        return incidenciaRepository.save(resultado);
    }

    public boolean update(Long idIncidencia, Incidencia incidencia) {
        Incidencia inc = incidenciaRepository.findById(idIncidencia);
        if(inc != null) {
            inc.setIdUsuarioReporte(incidencia.getIdUsuarioReporte());
            inc.setIdOperadorAsignado(incidencia.getIdOperadorAsignado());
            inc.setTitulo(incidencia.getTitulo());
            inc.setDescripcion(incidencia.getDescripcion());
            inc.setFechaIncidencia(incidencia.getFechaIncidencia());
            inc.setEstado(incidencia.getEstado());
            inc.setPrioridad(incidencia.getPrioridad());

            incidenciaRepository.save(inc);
            return true;
        }else{
            return false;
        }
    }

}
