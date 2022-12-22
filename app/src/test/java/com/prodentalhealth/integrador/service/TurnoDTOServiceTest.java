package com.prodentalhealth.integrador.service;


import com.prodentalhealth.integrador.dto.TurnoDTO;
import com.prodentalhealth.integrador.entity.Domicilio;
import com.prodentalhealth.integrador.entity.Odontologo;
import com.prodentalhealth.integrador.entity.Paciente;
import com.prodentalhealth.integrador.exceptions.BadRequestException;
import com.prodentalhealth.integrador.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TurnoDTOServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private TurnoDTOService turnoDTOService;

    @Test
    @Order(1)
    public void guardarTurnoTest() throws BadRequestException, ResourceNotFoundException {
        Odontologo odontologo = new Odontologo("4561313", "Lorena", "Grajales");
        Paciente paciente = new Paciente("1356232", "Juan", "Yepes", "juan@email.com", new Domicilio("Calle", 87, "Sur La Estrella", "Antioquia"), LocalDate.of(2022,12,12));
        odontologoService.guardarOdontologo(odontologo);
        pacienteService.guardarPaciente(paciente);
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setOdontologoId(odontologo.getId());
        turnoDTO.setPacienteId(paciente.getId());
        turnoDTO.setFecha(LocalDate.of(2022,12,12));
        TurnoDTO turnoDTOGuardado = turnoDTOService.guardarTurno(turnoDTO);
        assertEquals(1L, turnoDTOGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoXIdTest() throws BadRequestException, ResourceNotFoundException {
        Odontologo odontologo = new Odontologo("4561313", "Lorena", "Grajales");
        Paciente paciente = new Paciente("1356232", "Juan", "Yepes", "juan@email.com", new Domicilio("Calle", 87, "Sur La Estrella", "Antioquia"), LocalDate.of(2022,12,12));
        odontologoService.guardarOdontologo(odontologo);
        pacienteService.guardarPaciente(paciente);
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setOdontologoId(odontologo.getId());
        turnoDTO.setPacienteId(paciente.getId());
        turnoDTO.setFecha(LocalDate.of(2022,12,12));
        TurnoDTO turnoDTOGuardado = turnoDTOService.guardarTurno(turnoDTO);
        Optional<TurnoDTO> turnoDTOBuscado = turnoDTOService.buscarTurnoXId(turnoDTOGuardado.getId());
        assertNotNull(turnoDTOBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurnoTest() throws BadRequestException, ResourceNotFoundException {
        TurnoDTO turnoAActualizar = new TurnoDTO();
        Odontologo odontologo1 = new Odontologo("4561313", "Lorena", "Grajales");
        Odontologo odontologo2 = new Odontologo("4646323", "Pedro", "Montoya");
        Paciente paciente = new Paciente("1356232", "Juan", "Yepes", "juan@email.com", new Domicilio("Calle", 87, "Sur La Estrella", "Antioquia"), LocalDate.of(2022,12,12));
        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
        pacienteService.guardarPaciente(paciente);
        turnoAActualizar.setId(1L);
        turnoAActualizar.setOdontologoId(odontologo1.getId());
        turnoAActualizar.setPacienteId(paciente.getId());
        turnoAActualizar.setFecha(LocalDate.of(2022,12,12));
        turnoDTOService.guardarTurno(turnoAActualizar);
        turnoAActualizar.setOdontologoId(odontologo2.getId());
        turnoDTOService.actualizarTurno(turnoAActualizar);
        assertEquals(2L,turnoAActualizar.getOdontologoId());
    }

    @Test
    @Order(3)
    public void eliminarTurnoXIdTest() throws BadRequestException, ResourceNotFoundException {
        TurnoDTO turnoAEliminar = new TurnoDTO();
        Odontologo odontologo = new Odontologo("4561313", "Lorena", "Grajales");
        Paciente paciente = new Paciente("1356232", "Juan", "Yepes", "juan@email.com", new Domicilio("Calle", 87, "Sur La Estrella", "Antioquia"), LocalDate.of(2022,12,12));
        odontologoService.guardarOdontologo(odontologo);
        pacienteService.guardarPaciente(paciente);
        turnoAEliminar.setId(1L);
        turnoAEliminar.setOdontologoId(odontologo.getId());
        turnoAEliminar.setPacienteId(paciente.getId());
        turnoAEliminar.setFecha(LocalDate.of(2022,12,12));
        turnoDTOService.guardarTurno(turnoAEliminar);
        turnoDTOService.eliminarTurnoXId(turnoAEliminar.getId());
        List<TurnoDTO> turnoDTOS = turnoDTOService.listarTurnos();
        assertEquals(0,turnoDTOS.size());
    }

}
