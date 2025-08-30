package com.example.icfes_up.interfaces;

import com.google.gson.annotations.SerializedName;

public class DatosJWT {

    @SerializedName("id")
    private int id;

    @SerializedName("documento")
    private String documento;

    @SerializedName("rol")
    private String rol;

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("iat")
    private long iat;

    @SerializedName("exp")
    private long exp;

    // Constructor vacío
    public DatosJWT() {
    }

    // Constructor con parámetros
    public DatosJWT(int id, String documento, String rol, long timestamp, long iat, long exp) {
        this.id = id;
        this.documento = documento;
        this.rol = rol;
        this.timestamp = timestamp;
        this.iat = iat;
        this.exp = exp;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getRol() {
        return rol;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getIat() {
        return iat;
    }

    public long getExp() {
        return exp;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setIat(long iat) {
        this.iat = iat;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "DatosJWT{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", rol='" + rol + '\'' +
                ", timestamp=" + timestamp +
                ", iat=" + iat +
                ", exp=" + exp +
                '}';
    }
}
