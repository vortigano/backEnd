package com.porfolio.cam.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String apellido;
    @NotNull
    private String nombre;
    @NotNull
    private String subtitulo;
    @NotNull
    private String acerca_de_mi;
    @NotNull
    private String img_perfil;
    @NotNull
    private String img_banner;
    
    public Persona() {
    }

    public Persona(String apellido, String nombre, String sub_titulo, String acerca_de_mi, 
            String img_perfil, String img_banner) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.subtitulo = sub_titulo;
        this.acerca_de_mi = acerca_de_mi;
        this.img_perfil = img_perfil;
        this.img_banner = img_banner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
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
