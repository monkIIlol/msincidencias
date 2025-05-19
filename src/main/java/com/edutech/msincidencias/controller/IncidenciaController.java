package com.edutech.msincidencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edutech.msincidencias.model.Incidencia;
import com.edutech.msincidencias.service.IncidenciaService;


@RestController
@RequestMapping("api/v1/incidencias")
public class IncidenciaController {
    
    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping
    public ResponseEntity<List<Incidencia>> getAll() {
        List<Incidencia> incidencias = incidenciaService.findAll();
        if(!incidencias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
    }
    
}
