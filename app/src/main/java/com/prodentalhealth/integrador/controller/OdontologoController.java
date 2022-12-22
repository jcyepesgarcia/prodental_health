package com.prodentalhealth.integrador.controller;

import com.prodentalhealth.integrador.entity.Odontologo;
import com.prodentalhealth.integrador.exceptions.BadRequestException;
import com.prodentalhealth.integrador.exceptions.ResourceNotFoundException;
import com.prodentalhealth.integrador.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.status(HttpStatus.OK).body(odontologoService.listarOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoXId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(odontologoService.buscarOdontologoXId(id).get());
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarOdontologoXMatricula(@PathVariable String matricula) throws ResourceNotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(odontologoService.buscarOdontologoXMatricula(matricula).get());    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException{
        odontologoService.actualizarOdontologo(odontologo);
        return ResponseEntity.status(HttpStatus.OK).body("El registro id: [" + odontologo.getId() + "] de la tabla odontólogos fue actualizado con éxito.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologoXId(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologoXId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el registro id: [" + id + "] de la tabla odontólogos.");
    }
}
