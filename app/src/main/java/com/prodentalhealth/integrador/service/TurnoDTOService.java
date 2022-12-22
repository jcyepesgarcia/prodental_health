package com.prodentalhealth.integrador.service;

import com.prodentalhealth.integrador.dto.TurnoDTO;
import com.prodentalhealth.integrador.entity.Odontologo;
import com.prodentalhealth.integrador.entity.Paciente;
import com.prodentalhealth.integrador.entity.Turno;
import com.prodentalhealth.integrador.exceptions.BadRequestException;
import com.prodentalhealth.integrador.exceptions.ResourceNotFoundException;
import com.prodentalhealth.integrador.repository.OdontologoRepository;
import com.prodentalhealth.integrador.repository.PacienteRepository;
import com.prodentalhealth.integrador.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoDTOService {

    private TurnoRepository turnoRepository;
    private OdontologoRepository odontologoRepository;
    private PacienteRepository pacienteRepository;
    private static final Logger LOGGER= Logger.getLogger(DomicilioService.class);

    @Autowired
    public TurnoDTOService(TurnoRepository turnoRepository, OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository) {
        this.turnoRepository = turnoRepository;
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public TurnoDTO guardarTurno(TurnoDTO turnodto) throws BadRequestException, ResourceNotFoundException {
        LOGGER.info("Se inició el proceso de guardado del registro id: [" + turnodto.getId() + "] en la tabla turnos.");
        if(odontologoRepository.findById(turnodto.getOdontologoId()).isPresent() && pacienteRepository.findById(turnodto.getPacienteId()).isPresent()) {
            Turno nuevoTurno = turnoRepository.save(turnoDTOATurno(turnodto));
            return turnoATurnoDTO(nuevoTurno);
        }
        throw new ResourceNotFoundException("*** Error *** No se puede crear el registro. El pacienteId: [" + turnodto.getPacienteId() + "] o el odontologoId: [" + turnodto.getOdontologoId() + "] no existe en la base de datos.");
    }

    public Optional<TurnoDTO> buscarTurnoXId(Long id) throws ResourceNotFoundException{
        LOGGER.info("Se inició la búsqueda del registro id: [" + id + "] en la tabla turnos.");
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if(turnoBuscado.isPresent())
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        throw new ResourceNotFoundException("Oops! El registro id: [" + id + "] no existe en la tabla turnos.");
    }

    public void actualizarTurno(TurnoDTO turnodto) throws BadRequestException, ResourceNotFoundException{
        LOGGER.info("Se inició la actualización del registro id: [" + turnodto.getId() + "] de la tabla turnos.");
        Optional<Turno> turnoRequerido = turnoRepository.findById(turnodto.getId());
        if(turnoRequerido.isPresent()){
            if(odontologoRepository.findById(turnodto.getOdontologoId()).isPresent() && pacienteRepository.findById(turnodto.getPacienteId()).isPresent())
                turnoRepository.save(turnoDTOATurno(turnodto));
            else
                throw new BadRequestException("*** Error *** No se puede actualizar el registro id: [" + turnodto.getId() + "] de la tabla turnos. El pacienteId: [" + turnodto.getPacienteId() + "] o el odontologoId: [" + turnodto.getOdontologoId() + "] no existe en la base de datos.");
        } else
            throw new ResourceNotFoundException("Oops! No se puede actualizar el registro id: [" + turnodto.getId() + "] de la tabla turnos, porque el registro no existe en la base de datos.");
    }

    public void eliminarTurnoXId(Long id) throws ResourceNotFoundException{
        Optional<Turno> turnoRequerido = turnoRepository.findById(id);
        if(turnoRequerido.isPresent()) {
            LOGGER.warn("Se eliminó el registro id: [" + id + "] de la tabla turnos.");
            turnoRepository.deleteById(id);
        }else
            throw new ResourceNotFoundException("Oops! No se puede eliminar el registro id: [" + id + "] de la tabla turnos. El registro no existe en la base de datos.");
    }

    public List<TurnoDTO> listarTurnos(){
        LOGGER.info("Se ha iniciado el proceso de listado de todos los registro de la tabla turnos.");
        List<Turno> turnosEncontrados=turnoRepository.findAll();
        List<TurnoDTO> respuesta= new ArrayList<>();
        for (Turno turno:turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(turno));
        }
        return respuesta;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        LOGGER.warn("Se inició el proceso de conversión de la entidad turno a turnoDTO.");
        TurnoDTO respuesta= new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setFecha(turno.getFecha());
        return respuesta;
    }
    private Turno turnoDTOATurno(TurnoDTO turnodto){
        LOGGER.warn("Se inició el proceso de conversión de la entidad turnoDTO a turno.");
        Turno respuesta= new Turno();
        Odontologo odontologo= new Odontologo();
        Paciente paciente= new Paciente();
        odontologo.setId(turnodto.getOdontologoId());
        paciente.setId(turnodto.getPacienteId());
        respuesta.setFecha(turnodto.getFecha());
        respuesta.setId(turnodto.getId());
        respuesta.setOdontologo(odontologo);
        respuesta.setPaciente(paciente);
        return respuesta;
    }
}
