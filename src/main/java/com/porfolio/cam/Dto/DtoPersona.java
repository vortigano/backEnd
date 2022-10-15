package com.porfolio.cam.Dto;

public class DtoPersona {
    
    private String nombre;
    private String apellido;
    private String acerca_de_mi;
    private String img_perfil;
    private String img_banner;

    public DtoPersona() {
    }

    public DtoPersona(String nombre, String apellido, String acerca_de_mi, String img_perfil, String img_banner) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.acerca_de_mi = acerca_de_mi;
        this.img_perfil = img_perfil;
        this.img_banner = img_banner;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAcerca_de_mi() {
        return acerca_de_mi;
    }

    public void setAcerca_de_mi(String acerca_de_mi) {
        this.acerca_de_mi = acerca_de_mi;
    }

    public String getImg_perfil() {
        return img_perfil;
    }

    public void setImg_perfil(String img_perfil) {
        this.img_perfil = img_perfil;
    }

    public String getImg_banner() {
        return img_banner;
    }

    public void setImg_banner(String img_banner) {
        this.img_banner = img_banner;
    }
    
    
}
