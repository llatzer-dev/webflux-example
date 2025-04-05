package com.example.webfluxusuarios.model;

public class User {
    private String id;
    private String nombre;

    public User(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
