package com.prodentalhealth.integrador.controller;

import com.prodentalhealth.integrador.entity.Paciente;
import com.prodentalhealth.integrador.exceptions.BadRequestException;
import com.prodentalhealth.integrador.exceptions.ResourceNotFoundException;
import com.prodentalhealth.integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.guardarPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacienteXId(@PathVariable Long id) throws ResourceNotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.buscarPacienteXId(id).get());
    }

    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<Optional<Paciente>> buscarOdontologoXCedula(@PathVariable String cedula) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.buscarPacienteXCedula(cedula));  }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPacienteXEmail(@PathVariable String email) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.buscarPacienteXEmail(email));
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException{
        pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.OK).body("El registro id: [" + paciente.getId() + "] de la tabla pacientes fue actualizado con éxito.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPacienteXId(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPacienteXId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el registro id: [" + id + "] de la tabla pacientes.");
    }
}
