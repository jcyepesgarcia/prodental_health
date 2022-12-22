package com.prodentalhealth.integrador.service;

import com.prodentalhealth.integrador.entity.Paciente;
import com.prodentalhealth.integrador.exceptions.BadRequestException;
import com.prodentalhealth.integrador.exceptions.ResourceNotFoundException;
import com.prodentalhealth.integrador.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;
@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;
    private static final Logger LOGGER= Logger.getLogger(PacienteService.class);

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente(Paciente paciente) throws BadRequestException, ResourceNotFoundException {
        LOGGER.info("Se inició el proceso de guardado del registro id: [" + paciente.getId() + "] en la tabla pacientes.");
        Optional<Paciente> pacienteREmail = checkEmail(paciente.getEmail());
        Optional<Paciente> pacienteRCedula = checkCedula(paciente.getCedula());
        if(pacienteREmail.isPresent() || pacienteRCedula.isPresent())
            throw new BadRequestException("*** Error *** No se puede crear el registro. La cédula: [" + paciente.getCedula() + "] o el email: [" + paciente.getEmail() + "] proporcionado ya está registrado en la base de datos.");
        else
            return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPacienteXId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inició la búsqueda del registro id: [" + id + "] en la tabla pacientes.");
        Optional<Paciente> pacienteRequerido = pacienteRepository.findById(id);
        if(pacienteRequerido.isPresent())
            return Optional.of(pacienteRequerido.get());
        throw new ResourceNotFoundException("Oops! El registro id: [" + id + "] no existe en la tabla pacientes.");
    }

    public void actualizarPaciente(Paciente paciente) throws ResourceNotFoundException{
        LOGGER.info("Se inició la actualización del registro id: [" + paciente.getId() + "] de la tabla pacientes.");
        Optional<Paciente> pacienteRequerido = pacienteRepository.findById(paciente.getId());
        if(pacienteRequerido.isPresent())
            pacienteRepository.save(paciente);
        else
            throw new ResourceNotFoundException("*** Error *** No se puede actualizar el registro id: [" + paciente.getId() + "] de la tabla pacientes, porque el registro no existe en la base de datos.");
    }

    public void eliminarPacienteXId(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteResquerido = pacienteRepository.findById(id);
        if(pacienteResquerido.isPresent()) {
            LOGGER.warn("Se eliminó el registro id: [" + id + "] de la tabla pacientes.");
            pacienteRepository.deleteById(id);
        }else
            throw new ResourceNotFoundException("*** Error *** No se puede eliminar el registro id: [" + id + "] de la tabla pacientes, porque el registro no existe en la base de datos.");
    }

    public List<Paciente> listarPacientes(){
        LOGGER.info("Se ha iniciado el proceso de listado de todos los registro de la tabla pacientes.");
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> checkEmail(String email){
        return pacienteRepository.findByEmail(email);
    }

    public Optional<Paciente> checkCedula(String cedula){
        return pacienteRepository.findByCedula(cedula);
    }

    public Optional<Paciente> buscarPacienteXCedula(String cedula) throws ResourceNotFoundException {
        LOGGER.info("Se inició la búsqueda del paciente con cédula: [" + cedula + "] en la base de datos.");
        Optional<Paciente> paciente = pacienteRepository.findByCedula(cedula);
        if(paciente.isPresent())
            return Optional.of(paciente.get());
        throw new ResourceNotFoundException("Oops! El paciente con cédula: [" + cedula + "] no existe en la base de datos.");
    }

    public Optional<Paciente> buscarPacienteXEmail(String email) throws ResourceNotFoundException {
        LOGGER.info("Se inició la búsqueda del paciente con email: [" + email + "] en la base de datos.");
        Optional<Paciente> paciente = pacienteRepository.findByEmail(email);
        if(paciente.isPresent())
            return Optional.of(paciente.get());
        throw new ResourceNotFoundException("Oops! El paciente con email: [" + email + "] no existe en la base de datos.");
    }
}
