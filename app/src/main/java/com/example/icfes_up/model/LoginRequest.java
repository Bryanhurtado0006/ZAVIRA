package com.example.icfes_up.model;

public class LoginRequest {
    private String numeroDocumento;  // ⚠ debe coincidir con backend
    private String password;

    public LoginRequest(String numeroDocumento, String password) {
        this.numeroDocumento = numeroDocumento;
        this.password = password;
    }

    public String getNumeroDocumento() { return numeroDocumento; }
    public String getPassword() { return password; }
}
