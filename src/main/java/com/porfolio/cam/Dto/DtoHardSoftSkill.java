package com.porfolio.cam.Dto;

import javax.validation.constraints.NotBlank;

public class DtoHardSoftSkill {
    @NotBlank
    private String nombre;
    @NotBlank
    private String porcentaje;
    
    private String img_url;
    
    public DtoHardSoftSkill() {
    }

    public DtoHardSoftSkill(String nombre, String porcentaje, String img_url) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.img_url = img_url;
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
    
    public String getImg_url(){
        return img_url;
    }
    
    public void setImg_url(String img_url){
        this.img_url = img_url;
    }
}
