package com.prodentalhealth.integrador.service;

import com.prodentalhealth.integrador.entity.Odontologo;
import com.prodentalhealth.integrador.exceptions.BadRequestException;
import com.prodentalhealth.integrador.exceptions.ResourceNotFoundException;
import com.prodentalhealth.integrador.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;
    private static final Logger LOGGER= Logger.getLogger(PacienteService.class);

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    public Odontologo guardarOdontologo(Odontologo odontologo) throws BadRequestException, ResourceNotFoundException {
        LOGGER.info("Se inició el proceso de guardado del registro id: [" + odontologo.getId() + "] en la tabla odontólogos.");
        Optional<Odontologo> odontologoRequerido = checkMatricula(odontologo.getMatricula());
        if(odontologoRequerido.isPresent())
            throw new BadRequestException("*** Error *** No se puede crear el registro. La matrícula: [" + odontologo.getMatricula() + "] proporcionada ya está registrada en la base de datos.");
        else
            return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarOdontologoXId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inició la búsqueda del registro id: [" + id + "] en la tabla odontólogos.");
        Optional<Odontologo> odontologoRequerido = odontologoRepository.findById(id);
        if(odontologoRequerido.isPresent())
            return Optional.of(odontologoRequerido.get());
        throw new ResourceNotFoundException("Oops! El registro id: [" + id + "] no existe en la tabla odontólogos.");
    }

    public void actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException{
        LOGGER.info("Se inició la actualización del registro id: [" + odontologo.getId() + "] de la tabla odontólogos");
        Optional<Odontologo> odontologoRequerido = odontologoRepository.findById(odontologo.getId());
        if(odontologoRequerido.isPresent())
            odontologoRepository.save(odontologo);
        else
            throw new ResourceNotFoundException("*** Error *** No se puede actualizar el registro id: [" + odontologo.getId() + "] de la tabla odontólogos, porque el registro no existe en la base de datos.");
    }

    public void eliminarOdontologoXId(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoResquerido = odontologoRepository.findById(id);
        if(odontologoResquerido.isPresent()) {
            LOGGER.warn("Se eliminó el registro id: [" + id + "] de la tabla odontólogos.");
            odontologoRepository.deleteById(id);
        }else
            throw new ResourceNotFoundException("Oops! No se puede eliminar el registro id: [" + id + "] de la tabla odontólogos, porque el registro no existe en la base de datos.");
    }

    public List<Odontologo> listarOdontologos(){
        LOGGER.info("Se ha iniciado el proceso de listado de todos los registro de la tabla odontólogos.");
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> checkMatricula(String matricula){
        return odontologoRepository.findByMatricula(matricula);
    }

    public Optional<Odontologo> buscarOdontologoXMatricula(String matricula) throws ResourceNotFoundException{
        LOGGER.info("Se inició la búsqueda del odontólogo con matrícula: [" + matricula + "] en la base de datos.");
        Optional<Odontologo> odontologo = odontologoRepository.findByMatricula(matricula);
        if(odontologo.isPresent())
            return Optional.of(odontologo.get());
        throw new ResourceNotFoundException("Oops! El odontólogo con matrícula: [" + matricula + "] no existe en la base de datos.");
    }

}
