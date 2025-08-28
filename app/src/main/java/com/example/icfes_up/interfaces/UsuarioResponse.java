package com.example.icfes_up.model;  // ajusta el package si tu carpeta es diferente

import com.google.gson.annotations.SerializedName;

public class UsuarioResponse {

    @SerializedName("estudiante")
    private String estudiante; // "Login exitoso"

    @SerializedName("token")
    private String token;      // JWT recibido desde backend

    // Constructor vacío (necesario para Gson)
    public UsuarioResponse() {
    }

    // Constructor con parámetros
    public UsuarioResponse(String estudiante, String token) {
        this.estudiante = estudiante;
        this.token = token;
    }

    // Getters
    public String getEstudiante() {
        return estudiante;
    }

    public String getToken() {
        return token;
    }

    // Setters
    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Para debug/logs
    @Override
    public String toString() {
        return "UsuarioResponse{" +
                "estudiante='" + estudiante + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
