package com.porfolio.cam.Dto;

import javax.validation.constraints.NotBlank;

public class DtoHardSoftSkill {
    @NotBlank
    private String nombre;
    @NotBlank
    private float porcentaje;

    public DtoHardSoftSkill() {
    }

    public DtoHardSoftSkill(String nombre, int porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
