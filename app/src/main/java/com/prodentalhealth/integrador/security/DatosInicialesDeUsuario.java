package com.prodentalhealth.integrador.security;

import com.prodentalhealth.integrador.entity.Usuario;
import com.prodentalhealth.integrador.entity.UsuarioRole;
import com.prodentalhealth.integrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesDeUsuario implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String userPassSinCifrar = "userpass";
        String userPassCifrada = cifrador.encode(userPassSinCifrar);
        Usuario user = new Usuario("User Test", "user_test", "user@email.com", userPassCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(user);

        String adminPassSinCifrar = "adminpass";
        String adminPassCifrada = cifrador.encode(adminPassSinCifrar);
        Usuario admin = new Usuario("Admin Test", "admin_test", "admin@email.com", adminPassCifrada, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(admin);
    }
}
