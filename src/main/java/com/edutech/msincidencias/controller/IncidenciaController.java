package com.edutech.msincidencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.edutech.msincidencias.model.Incidencia;
import com.edutech.msincidencias.service.IncidenciaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




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

    @GetMapping("/{idIncidencia}")
    public ResponseEntity<Incidencia> readIncidencia(@PathVariable int idIncidencia) {
        Incidencia buscar = incidenciaService.findById(idIncidencia);
        if(buscar != null) {
            return new ResponseEntity<>(buscar, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idIncidencia}/estado")
    public ResponseEntity<?> estado(@PathVariable int idIncidencia) {
        Incidencia buscar = incidenciaService.findById(idIncidencia);
        if(buscar != null) {
            return new ResponseEntity<>(buscar.getEstado(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @PutMapping("/{idIncidencia}")
    public ResponseEntity<Incidencia> updateIncidencia(@PathVariable int idIncidencia, @RequestBody Incidencia incidencia) {
        if(incidenciaService.update(idIncidencia, incidencia)) {
            return new ResponseEntity<>(incidencia, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }
    
    @DeleteMapping("/{idIncidencia}")
    public ResponseEntity<?> deleteIncidencia(@PathVariable int idIncidencia) {
        Incidencia buscar = incidenciaService.findById(idIncidencia);
        if(buscar != null) {
            incidenciaService.deleteById(idIncidencia);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}