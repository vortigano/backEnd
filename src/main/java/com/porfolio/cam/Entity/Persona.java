package com.porfolio.cam.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 80, message = "Longitud invalida 1 a 80")
    private String nombre;
    
    @NotNull
    @Size(min = 1, max = 80, message = "Longitud invalida 1 a 80")
    private String apellido;
    
    @NotNull
    @Size(min = 1, max = 80, message = "Longitud invalida 1 a 80")
    private String img;

}
