package com.prodentalhealth.integrador.service;

import com.prodentalhealth.integrador.entity.Domicilio;
import com.prodentalhealth.integrador.exceptions.ResourceNotFoundException;
import com.prodentalhealth.integrador.repository.DomicilioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {
    private DomicilioRepository domicilioRepository;
    private static final Logger LOGGER= Logger.getLogger(DomicilioService.class);

    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Optional<Domicilio> buscarDomicilioXId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inició la búsqueda del registro id: [" + id + "] en la tabla domicilios");
        Optional<Domicilio> domicilioRequerido = domicilioRepository.findById(id);
        if(domicilioRequerido.isPresent())
            return Optional.of(domicilioRequerido.get());
        throw new ResourceNotFoundException("Oops! El registro id: [" + id + "] no existe en la tabla domicilios.");
    }

//    public List<Domicilio> listarDomicilios(){
//        LOGGER.info("Se ha iniciado el listado de todos los registro de la tabla domicilios");
//        return domicilioRepository.findAll();
//    }

//    public void actualizarDomicilio(Domicilio domicilio) throws ResourceNotFoundException {
//        LOGGER.info("Se inició la actualización del registro id: [" + domicilio.getId() + "] de la tabla domicilios");
//        Optional<Domicilio> domicilioRequerido = domicilioRepository.findById(domicilio.getId());
//        if(domicilioRequerido.isPresent())
//            domicilioRepository.save(domicilio);
//        else
//            throw new ResourceNotFoundException("*** Error *** No se puede actualizar el registro id: [" + domicilio.getId() + "] de la tabla domicilios. El registro no existe en la base de datos.");
//    }

}
