package com.porfolio.cam.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"${settings.cors_origin.remote}","${settings.cors_origin.local}"})
public class CPersona {
    @Autowired SPersona spersona;
    
    @GetMapping("personas/traer")
    public List<Persona> getPersona(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " - TRAYENDO...");
        return spersona.getPersona();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("personas/crear")
    public String createPersona(@RequestBody Persona persona){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " - CREANDO...");
        spersona.savePersona(persona);
        return "La persona se creó correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " - BORRANDO...");
        spersona.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    //PUERTO/personas/editar/<id>/<nombre>&<apellido>&<img>
    @PutMapping("personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                                @RequestParam("nombre") String nuevoNombre,
                                @RequestParam("apellido") String nuevoApellido,
                                @RequestParam("img") String nuevaImg){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " - EDITADO...");
        Persona persona = spersona.findPersona(id);
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevaImg);
        
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
