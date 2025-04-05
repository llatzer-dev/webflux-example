package com.example.webfluxusuarios.controller;

import com.example.webfluxusuarios.model.User;
import com.example.webfluxusuarios.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    private final UserRepository userRepository = new UserRepository();

    @GetMapping
    public Flux<User> listarUsuarios() {
        return userRepository.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Mono<User> obtenerUsuarioPorId(@PathVariable String id) {
        return userRepository.buscarPorId(id);
    }
}
