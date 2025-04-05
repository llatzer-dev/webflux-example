package com.example.webfluxusuarios.repository;

import com.example.webfluxusuarios.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private final Map<String, User> baseDatos = new ConcurrentHashMap<>();

    public UserRepository() {
        baseDatos.put("1", new User("1", "Lázaro"));
        baseDatos.put("2", new User("2", "Andrea"));
        baseDatos.put("3", new User("3", "Carlos"));
    }

    public Flux<User> obtenerTodos() {
        return Flux.fromIterable(baseDatos.values())
                .doOnNext(user -> System.out.println("Emitido: " + user.getNombre()));
    }

    public Mono<User> buscarPorId(String id) {
        return Mono.justOrEmpty(baseDatos.get(id))
                .<User>handle((user, sink) -> {
                    if (user.getNombre().equals("Carlos")) {
                        sink.error(new RuntimeException("Carlos está bloqueado"));
                    } else {
                        sink.next(user);
                    }
                })
                .doOnSubscribe(sub -> System.out.println("Suscribiéndose a buscarPorId"))
                .doOnSuccess(user -> System.out.println("Éxito al buscar: " + (user != null ? user.getNombre() : "Nada")))
                .doOnError(error -> System.err.println("Error al buscar: " + error.getMessage()))
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")));
    }
}
