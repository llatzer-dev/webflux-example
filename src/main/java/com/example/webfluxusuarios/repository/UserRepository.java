package com.example.webfluxusuarios.repository;

import com.example.webfluxusuarios.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private final Map<String, User> baseDatos = new ConcurrentHashMap<>();

    public UserRepository() {
        baseDatos.put("1", new User("1", "Lázaro","lazaro@example.com"));
        baseDatos.put("2", new User("2", "Andrea", "andrea@example.com"));
        baseDatos.put("3", new User("3", "Carlos", "carlos@example.com"));
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

    // Método para crear un nuevo usuario
    public Mono<User> crearUsuario(User user) {
        if (baseDatos.containsKey(user.getId())) {
            return Mono.error(new RuntimeException("El usuario con este ID ya existe"));
        } else {
            baseDatos.put(user.getId(), user);
            return Mono.just(user);
        }
    }

    // Método para actualizar un usuario existente
    public Mono<User> actualizarUsuario(String id, User user) {
        if (baseDatos.containsKey(id)) {
            user.setId(id); // Aseguramos que el ID se mantenga
            baseDatos.put(id, user);
            return Mono.just(user);
        } else {
            return Mono.error(new RuntimeException("Usuario no encontrado para actualizar"));
        }
    }

    // Método para eliminar un usuario
    public Mono<Void> eliminarUsuario(String id) {
        if (baseDatos.containsKey(id)) {
            baseDatos.remove(id);
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("Usuario no encontrado para eliminar"));
        }
    }

}
