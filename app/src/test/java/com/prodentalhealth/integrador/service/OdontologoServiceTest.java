package com.prodentalhealth.integrador.service;

import com.prodentalhealth.integrador.entity.Odontologo;
import com.prodentalhealth.integrador.exceptions.BadRequestException;
import com.prodentalhealth.integrador.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologoTest() throws BadRequestException, ResourceNotFoundException {
        Odontologo odontologo = new Odontologo("4561313", "Lorena", "Grajales");
        odontologoService.guardarOdontologo(odontologo);
        assertEquals(1L, odontologo.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoXIdTest() throws ResourceNotFoundException, BadRequestException {
        Odontologo odontologo = new Odontologo("4561313", "Lorena", "Grajales");
        odontologoService.guardarOdontologo(odontologo);
        Long odontologoId = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoXId(odontologoId);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologoTest() throws ResourceNotFoundException, BadRequestException {
        Odontologo odontologo = new Odontologo("4561313", "Lorena", "Grajales");
        odontologoService.guardarOdontologo(odontologo);
        odontologoService.actualizarOdontologo(new Odontologo(1l,"4561313", "Lorena", "Ruiz"));
        Optional< Odontologo> odontologoActualizado = odontologoService.buscarOdontologoXId(odontologo.getId());
        assertEquals("Ruiz", odontologoActualizado.get().getApellido());
    }

    @Test
    @Order(4)
    public void eliminarOdontologoXIdTest() throws ResourceNotFoundException, BadRequestException {
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        Odontologo odontologo = new Odontologo("4561313", "Lorena", "Grajales");
        odontologoService.guardarOdontologo(odontologo);
        odontologoService.eliminarOdontologoXId(odontologo.getId());
        assertEquals(0,odontologos.size());
    }

    @Test
    @Order(5)
    public void listarOdontologosTest() throws BadRequestException, ResourceNotFoundException {
        Odontologo odontologo = new Odontologo("4561313", "Lorena", "Grajales");
        odontologoService.guardarOdontologo(odontologo);
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        assertEquals(1,odontologos.size());
    }
}
