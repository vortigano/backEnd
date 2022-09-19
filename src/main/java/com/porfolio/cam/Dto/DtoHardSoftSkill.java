package com.porfolio.cam.Dto;

import javax.validation.constraints.NotBlank;

public class DtoHardSoftSkill {
    @NotBlank
    private String nombre;
    @NotBlank
    private String porcentaje;

    public DtoHardSoftSkill() {
    }

    public DtoHardSoftSkill(String nombre, String porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
}
