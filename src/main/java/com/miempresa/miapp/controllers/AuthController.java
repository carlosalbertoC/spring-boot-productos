package com.miempresa.miapp.controllers;

import com.miempresa.miapp.models.Usuario;
import com.miempresa.miapp.security.JwtUtil;
import com.miempresa.miapp.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Map<String, String> request) {
        Usuario usuario = authService.registrar(request.get("username"), request.get("password"), "USER");
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<Usuario> usuario = authService.buscarPorUsername(request.get("username"));

        if (usuario.isPresent() && authService.validarPassword(request.get("password"), usuario.get().getPassword())) {
            String token = jwtUtil.generarToken(usuario.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }
}
