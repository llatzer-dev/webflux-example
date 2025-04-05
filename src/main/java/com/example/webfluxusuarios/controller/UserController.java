package com.example.webfluxusuarios.controller;

import com.example.webfluxusuarios.model.User;
import com.example.webfluxusuarios.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
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

    // Crear un nuevo usuario
    @PostMapping
    public Mono<User> crearUsuario(@RequestBody User user) {
        return userRepository.crearUsuario(user);
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public Mono<User> actualizarUsuario(@PathVariable String id, @RequestBody User user) {
        System.out.println("Usuario recibido para actualización: " + user);
        return userRepository.actualizarUsuario(id, user);
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public Mono<Void> eliminarUsuario(@PathVariable String id) {
        return userRepository.eliminarUsuario(id);
    }
}
