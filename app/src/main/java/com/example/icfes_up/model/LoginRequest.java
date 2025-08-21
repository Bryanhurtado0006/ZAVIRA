package com.example.icfes_up.model;

public class LoginRequest {

    private String documento;
    private String contrasena;

    public LoginRequest(String documento, String contrasena) {
        this.documento = documento;
        this.contrasena = contrasena;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
