package com.prodentalhealth.integrador.service;

import com.prodentalhealth.integrador.entity.Usuario;
import com.prodentalhealth.integrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional< Usuario > usuarioBuscado = usuarioRepository.findByEmail(username);
        if(usuarioBuscado.isPresent())
            return usuarioBuscado.get();
        else
            throw new UsernameNotFoundException("El usuario ingresado no existe en la base de datos.");
    }
}
