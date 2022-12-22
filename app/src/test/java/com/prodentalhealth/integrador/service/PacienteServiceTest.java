package com.prodentalhealth.integrador.service;

import com.prodentalhealth.integrador.entity.Domicilio;
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

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest() throws BadRequestException, ResourceNotFoundException {
        Paciente paciente = new Paciente("1356232", "Juan", "Yepes", "juan@email.com", new Domicilio("Calle", 87, "Sur La Estrella", "Antioquia"), LocalDate.of(2022,12,12));
        pacienteService.guardarPaciente(paciente);
        assertEquals(1L, paciente.getId());
    }

    @Test
    @Order(2)
    public void buscarPacienteXIdTest() throws ResourceNotFoundException, BadRequestException {
        Paciente paciente = new Paciente("1356232", "Juan", "Yepes", "juan@email.com", new Domicilio("Calle", 87, "Sur La Estrella", "Antioquia"), LocalDate.of(2022,12,12));
        pacienteService.guardarPaciente(paciente);
        Long pacienteId = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteXId(pacienteId);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarPacienteTest() throws ResourceNotFoundException, BadRequestException {
        Paciente paciente = new Paciente("1356232", "Juan", "Yepes", "juan@email.com", new Domicilio("Calle", 87, "Sur La Estrella", "Antioquia"), LocalDate.of(2022,12,12));
        pacienteService.guardarPaciente(paciente);
        pacienteService.actualizarPaciente(new Paciente(1L,"1356232", "Carlos", "García", "carlos@email.com", new Domicilio("Calle", 87, "Sur La Estrella", "Antioquia"), LocalDate.of(2022,12,12)));
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPacienteXId(paciente.getId());
        assertEquals("García", pacienteActualizado.get().getApellido());
    }

    @Test
    @Order(4)
    public void eliminarPacienteXIdTest() throws ResourceNotFoundException, BadRequestException {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        Paciente paciente = new Paciente("1356232", "Juan", "Yepes", "juan@email.com", new Domicilio("Calle", 87, "Sur La Estrella", "Antioquia"), LocalDate.of(2022,12,12));
        pacienteService.guardarPaciente(paciente);
        pacienteService.eliminarPacienteXId(paciente.getId());
        assertEquals(0,pacientes.size());
    }

    @Test
    @Order(5)
    public void listarPacientesTest() throws BadRequestException, ResourceNotFoundException {
        Paciente paciente = new Paciente("1356232", "Juan", "Yepes", "juan@email.com", new Domicilio("Calle", 87, "Sur La Estrella", "Antioquia"), LocalDate.of(2022,12,12));
        pacienteService.guardarPaciente(paciente);
        List<Paciente> pacientes = pacienteService.listarPacientes();
        assertEquals(1,pacientes.size());
    }

}
