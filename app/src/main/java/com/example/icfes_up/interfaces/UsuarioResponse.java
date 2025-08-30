package com.example.icfes_up.interfaces;

public class UsuarioResponse {
    private String estudiante; // "Login exitoso"
    private String token;      // JWT

    public String getEstudiante() { return estudiante; }
    public String getToken() { return token; }
}
