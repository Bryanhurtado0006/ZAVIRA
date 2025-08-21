package com.example.icfes_up.Mundos.competencia;

import java.util.List;

public class Competencia {
    private String nombre;
    private List<String> subtemas;

    public Competencia(String nombre, List<String> subtemas) {
        this.nombre   = nombre;
        this.subtemas = subtemas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getSubtemas() {
        return subtemas;
    }
}
