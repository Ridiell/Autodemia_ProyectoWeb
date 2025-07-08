
package com.autodemia.forms;

import java.util.List;

public class CursoForm {
    private String nombre;
    private String descripcion;
    private List<ModuloForm> modulos;

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ModuloForm> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloForm> modulos) {
        this.modulos = modulos;
    }
}
