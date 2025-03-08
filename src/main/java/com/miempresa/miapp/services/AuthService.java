package com.miempresa.miapp.services;

import com.miempresa.miapp.models.Usuario;
import com.miempresa.miapp.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrar(String username, String password, String role) {
        String passwordEncriptada = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(username, passwordEncriptada, Set.of(role));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public boolean validarPassword(String password, String passwordHash) {
        return passwordEncoder.matches(password, passwordHash);
    }
}