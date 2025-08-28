package com.example.icfes_up.model;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private int grado;
    private String curso;
    private String jornada;
    private String correo;
    private String rol;
    private int idInstitucion;

    // Getters
    public int getIdUsuario() { return idUsuario; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getApellido() { return apellido; }
    public String getTipoDocumento() { return tipoDocumento; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public int getGrado() { return grado; }
    public String getCurso() { return curso; }
    public String getJornada() { return jornada; }
    public String getCorreo() { return correo; }
    public String getRol() { return rol; }
    public int getIdInstitucion() { return idInstitucion; }
}
