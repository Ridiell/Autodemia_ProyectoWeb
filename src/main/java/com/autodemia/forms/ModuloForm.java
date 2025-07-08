package com.autodemia.forms;

import java.util.List;

public class ModuloForm {
    private String nombre;
    private List<ClaseForm> clases;

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ClaseForm> getClases() {
        return clases;
    }

    public void setClases(List<ClaseForm> clases) {
        this.clases = clases;
    }
}