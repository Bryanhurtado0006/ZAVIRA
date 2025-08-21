package com.example.icfes_up.model;

public class UsuarioResponse {
    private String nombre;
    private String token; // o lo que devuelva tu API

    // Getters y setters opcionales


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
