package com.porfolio.cam.Controller;

import com.porfolio.cam.Dto.DtoHardSoftSkill;
import com.porfolio.cam.Entity.HardSoftSkill;
import com.porfolio.cam.Security.Controller.Mensaje;
import com.porfolio.cam.Service.SHardSoftSkill;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hardsoftskill")
@CrossOrigin(origins = {"${settings.cors_origin.remote}","${settings.cors_origin.local}"})
public class CHardSoftSkill {
    @Autowired
    SHardSoftSkill sHardSoftSkill;
    
    //trae la lista
    @GetMapping("/lista")
    public ResponseEntity<List<HardSoftSkill>> list(){
        List<HardSoftSkill> list= sHardSoftSkill.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<HardSoftSkill> getById(@PathVariable("id") int id){
        if(!sHardSoftSkill.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        HardSoftSkill skill = sHardSoftSkill.getOne(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }
    
    //borrar dato
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //valida el ID
        if(!sHardSoftSkill.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        sHardSoftSkill.delete(id);
        
        return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
    }
    
    //crea elementos
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHardSoftSkill dtoSkill){
        if(StringUtils.isBlank(dtoSkill.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sHardSoftSkill.existsByNombre(dtoSkill.getNombre()))
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        //si pasa todas estas validaciones...
        HardSoftSkill skill = new HardSoftSkill(dtoSkill.getNombre(), dtoSkill.getPorcentaje());
        sHardSoftSkill.save(skill);
        
        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    //actualiza la lista
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHardSoftSkill dtoSkill){
        //validación antes de actualizar
        //valida el ID
        if(!sHardSoftSkill.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        //compara nombres de las experiencias
        if(sHardSoftSkill.existsByNombre(dtoSkill.getNombre()) && 
                sHardSoftSkill.getByNombre(dtoSkill.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }
        //no puede estar en blanco (nombre)
        if(StringUtils.isBlank(dtoSkill.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        //TODO:
        //no puede estar en blanco/nulo (porcentaje) y debería prevenir tipo de dato no numerico
        try{
            float es_un_numero = dtoSkill.getPorcentaje();
            System.out.format("Porcentaje ingresado: %f", es_un_numero);
        }catch(java.lang.NumberFormatException e)
        {
            return new ResponseEntity(new Mensaje("Formato de porcentaje incorrecto"), HttpStatus.BAD_REQUEST);
        }catch(java.lang.NullPointerException e)
        {
            return new ResponseEntity(new Mensaje("El porcentaje no puede estar vacío"), HttpStatus.BAD_REQUEST);
        }
        
        //finalmente actualiza el dato
        
        HardSoftSkill skill = sHardSoftSkill.getOne(id).get();
        skill.setNombre(dtoSkill.getNombre());
        skill.setPorcentaje(dtoSkill.getPorcentaje());
        
        sHardSoftSkill.save(skill);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
    }
}
