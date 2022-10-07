package com.porfolio.cam.Controller;

import com.porfolio.cam.Dto.DtoHardSoftSkill;
import com.porfolio.cam.Dto.DtoProyecto;
import com.porfolio.cam.Entity.HardSoftSkill;
import com.porfolio.cam.Entity.Proyecto;
import com.porfolio.cam.Security.Controller.Mensaje;
import com.porfolio.cam.Service.SProyecto;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = {"${settings.cors_origin.remote}","${settings.cors_origin.local}"})
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    //trae la lista
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list= sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<HardSoftSkill> getById(@PathVariable("id") int id){
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    //borrar dato
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //valida el ID
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        sProyecto.delete(id);
        
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
    
    //crea elementos
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyecto dtoProyecto){
        if(StringUtils.isBlank(dtoProyecto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sProyecto.existsByNombre(dtoProyecto.getNombre()))
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        //si pasa todas estas validaciones...
        Proyecto proyecto = new Proyecto(dtoProyecto.getNombre(),
                                            dtoProyecto.getDescripcion(),
                                            dtoProyecto.getImg());
        sProyecto.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }
    
    //actualiza la lista
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyecto dtoProyecto){
        //validación antes de actualizar
        //valida el ID
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        //compara nombres de las experiencias
        if(sProyecto.existsByNombre(dtoProyecto.getNombre()) && 
                sProyecto.getByNombre(dtoProyecto.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        //no puede estar en blanco (nombre)
        if(StringUtils.isBlank(dtoProyecto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        //no puede estar en blanco (descipcion)
        if(StringUtils.isBlank(dtoProyecto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        //la img PUEDE estar vacía por lo tanto no hago valicadiones
        
        Proyecto proyecto = sProyecto.getOne(id).get();
        proyecto.setNombre(dtoProyecto.getNombre());
        proyecto.setDescripcion(dtoProyecto.getDescripcion());
        proyecto.setImg(dtoProyecto.getImg());
        
        sProyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("proyecto actualizado"), HttpStatus.OK);
    }
}
