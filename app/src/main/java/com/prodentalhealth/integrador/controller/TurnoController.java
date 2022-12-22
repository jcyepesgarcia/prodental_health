package com.prodentalhealth.integrador.controller;

import com.prodentalhealth.integrador.dto.TurnoDTO;
import com.prodentalhealth.integrador.exceptions.BadRequestException;
import com.prodentalhealth.integrador.exceptions.ResourceNotFoundException;
import com.prodentalhealth.integrador.service.TurnoDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoDTOService turnoDTOService;
    @Autowired
    public TurnoController(TurnoDTOService turnoDTOService) {
        this.turnoDTOService = turnoDTOService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turnodto) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(turnoDTOService.guardarTurno(turnodto));
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        return ResponseEntity.status(HttpStatus.OK).body(turnoDTOService.listarTurnos());
    }

    @GetMapping("/{id}")
    public Optional<TurnoDTO> buscarTurnoXId(@PathVariable Long id) throws ResourceNotFoundException {
        return turnoDTOService.buscarTurnoXId(id);
    }

    @PutMapping()
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turnodto) throws BadRequestException, ResourceNotFoundException{
        turnoDTOService.actualizarTurno(turnodto);
        return ResponseEntity.status(HttpStatus.OK).body("El registro id: [" + turnodto.getId() + "] de la tabla turnos fue actualizado con éxito.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurnoXId(@PathVariable Long id) throws ResourceNotFoundException {
        turnoDTOService.eliminarTurnoXId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el registro id: [" + id + "] de la tabla turnos.");
    }
}
