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
        HardSoftSkill skill = new HardSoftSkill(dtoSkill.getNombre(),
                                                dtoSkill.getPorcentaje(),
                                                dtoSkill.getImg_url());
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
        //no puede estar en blanco (porcentaje) ni ser menor que cero o mayor que cien
        System.out.format("Valor > %s < \n", dtoSkill.getPorcentaje());
        System.err.println("pos-1");
        if(StringUtils.isBlank(dtoSkill.getPorcentaje()))
            return new ResponseEntity(new Mensaje("El porcentaje no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        //check formato de argumento porcentaje, esperando formato decimal
        try{
            Double.parseDouble(dtoSkill.getPorcentaje());
        }catch(NumberFormatException e){
            System.err.println("Formato de porcentaje no válido");
            return new ResponseEntity(new Mensaje("Formato de porcentaje no válido"), HttpStatus.BAD_REQUEST);
            //not double
        }
        //0<=x<=100
        if(Double.valueOf(dtoSkill.getPorcentaje())<0 || Double.valueOf(dtoSkill.getPorcentaje())>100)
            return new ResponseEntity(new Mensaje("El porcentaje está fuera de rango"), HttpStatus.BAD_REQUEST);
        
        //la imgUrl PUEDE estar vacía por lo tanto no hago valicadiones
        
        
        HardSoftSkill skill = sHardSoftSkill.getOne(id).get();
        skill.setNombre(dtoSkill.getNombre());
        skill.setPorcentaje(dtoSkill.getPorcentaje());
        skill.setImg_url(dtoSkill.getImg_url());
        //skill.setImgUrl("cadena desde cóidog server...");
        
        System.out.format("valor imgUrl recibido: %s\n", dtoSkill.getImg_url());
        System.out.format("valor seteado en skill imgUrl: %s\n", skill.getImg_url());
        
        sHardSoftSkill.save(skill);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
    }
}
