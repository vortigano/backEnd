package com.porfolio.cam.Controller;

import com.porfolio.cam.Dto.DtoPersona;
import com.porfolio.cam.Entity.Persona;
import com.porfolio.cam.Service.SPersona;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"${settings.cors_origin.remote}","${settings.cors_origin.local}"})
public class CPersona {
    @Autowired SPersona spersona;
    
    @GetMapping("/traer")
    public List<Persona> getPersona(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " - TRAYENDO...");
        return spersona.getPersona();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public String createPersona(@RequestBody Persona persona){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " - CREANDO...");
        spersona.savePersona(persona);
        return "La persona se creó correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " - BORRANDO...");
        spersona.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    //esto cambia el registro completo, luego necesito
    //especializar la api para cada sección
    @PutMapping("/editar/{id}")
    public Persona editPersona(@PathVariable Long id, @RequestBody DtoPersona dtoPersona){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " - EDITADO...");
        Persona persona = spersona.findPersona(id);
        persona.setNombre(dtoPersona.getNombre());
        persona.setApellido(dtoPersona.getApellido());
        persona.setSubtitulo(dtoPersona.getSubtitulo());
        persona.setAcerca_de_mi(dtoPersona.getAcerca_de_mi());
        persona.setImg_perfil(dtoPersona.getImg_perfil());
        persona.setImg_banner(dtoPersona.getImg_banner());
        
        spersona.savePersona(persona);
        return persona;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/borrarAcercaDeMi/{id}")
    public Persona borrarPersonaDescripcion(@PathVariable Long id, @RequestBody DtoPersona dtoPersona){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " - BORRANDO DESCRIPCION...");
        Persona persona = spersona.findPersona(id);
        persona.setNombre(dtoPersona.getNombre());
        persona.setApellido(dtoPersona.getApellido());
        persona.setSubtitulo(dtoPersona.getSubtitulo());
        persona.setAcerca_de_mi("");
        persona.setImg_perfil(dtoPersona.getImg_perfil());
        persona.setImg_banner(dtoPersona.getImg_banner());
        
        spersona.savePersona(persona);
        return persona;
    }
    
    //@GetMapping("personas/traer/perfil")
    @GetMapping("${api.persona_perfil.get}")
    public Persona findPersona(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " - Trayendo perfil...");
        return spersona.findPersona((long)1);
    }
}
