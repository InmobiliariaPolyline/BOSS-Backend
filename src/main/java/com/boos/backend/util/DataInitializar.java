package com.boos.backend.util;

import com.boos.backend.Model.Rol;
import com.boos.backend.Model.Usuario;
import com.boos.backend.Repository.IRolRepository;
import com.boos.backend.Repository.IUsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializar implements ApplicationRunner {
    private final IUsuariosRepository usuarioRepository;
    private final IRolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 1. Crear roles si no existen
        Rol jefeRol = rolRepository.findByNombreRol("JEFE_OBRA")
                .orElseGet(() -> rolRepository.save(new Rol("JEFE_OBRA")));

        Rol asistenteRol = rolRepository.findByNombreRol("ASISTENTE")
                .orElseGet(() -> rolRepository.save(new Rol("ASISTENTE")));

        Rol developerRol = rolRepository.findByNombreRol("DEVELOPER")
                .orElseGet(() -> rolRepository.save(new Rol("DEVELOPER")));

        // 2. Crear usuario Developer si no existe
        if (!usuarioRepository.existsByUsername("developer")) {
            Usuario developer = new Usuario();
            developer.setUsername("developer");
            developer.setPassword(passwordEncoder.encode("developer123"));
            developer.setEstado(true);
            developer.setRoles(List.of(developerRol));
            usuarioRepository.save(developer);
            System.out.println("✅ Usuario 'developer' creado");
        }
    }
}
