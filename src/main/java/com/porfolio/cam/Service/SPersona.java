package com.porfolio.cam.Service;

import com.porfolio.cam.Entity.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.porfolio.cam.Repository.RPersona;

@Service
public class SPersona{
    @Autowired RPersona ipersonaRepository;
    
    //Traer una lita de Personas
    public List<Persona> getPersona(){
        List<Persona> persona = ipersonaRepository.findAll();
        return persona;
    }
    
    //Guardar un objeto de tipo Persona
    public void savePersona(Persona persona){
        ipersonaRepository.save(persona);
    }
    
    //Eliminar un objeto pero lo buscamos por ID
    public void deletePersona(Long id){
        ipersonaRepository.deleteById(id);
    }
    
    //Buscar una persona por ID
    public Persona findPersona(Long id){
        Persona persona = ipersonaRepository.findById(id).orElse(null);
        return persona;
    }
    
}
