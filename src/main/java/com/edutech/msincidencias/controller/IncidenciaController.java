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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/v1/incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping
    public ResponseEntity<List<Incidencia>> getAll() {
        List<Incidencia> incidencias = incidenciaService.findAll();
        if(!incidencias.isEmpty()) {
            return new ResponseEntity<>(incidencias,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }   
    }
    
    @PostMapping()
    public ResponseEntity<Incidencia> postIncidencia(@RequestBody Incidencia incidencia) {
        Incidencia buscar = incidenciaService.findById(incidencia.getIdIncidencia());
        if(buscar == null) {
            return new ResponseEntity<>(incidenciaService.save(incidencia), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
}
