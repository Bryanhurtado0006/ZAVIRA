package com.example.icfes_up.interfaces;

public class PerfilResponse {
    private String mensaje;
    private Datos datos;

    public String getMensaje() { return mensaje; }
    public Datos getDatos() { return datos; }

    public static class Datos {
        private int id;
        private String documento;
        private String rol;
        private long timestamp;

        public int getId() { return id; }
        public String getDocumento() { return documento; }
        public String getRol() { return rol; }
        public long getTimestamp() { return timestamp; }
    }
}
