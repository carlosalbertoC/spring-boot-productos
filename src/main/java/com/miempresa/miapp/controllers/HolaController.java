package com.miempresa.miapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Indica que esta clase maneja peticiones REST
@RequestMapping("/api")
public class HolaController {

    @GetMapping("/hola")
    public String holaMundo() {
        return "¡Hola, mundo desde Spring Boot!";
    }
}
