package com.porfolio.cam.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HardSoftSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String porcentaje;
    private String img_url;

    public HardSoftSkill() {
    }

    public HardSoftSkill(String nombre, String porcentaje, String img_url) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.img_url = img_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
