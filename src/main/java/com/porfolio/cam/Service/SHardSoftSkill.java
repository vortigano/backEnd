package com.porfolio.cam.Service;

import com.porfolio.cam.Entity.HardSoftSkill;
import com.porfolio.cam.Repository.RHardSoftSkill;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SHardSoftSkill {
    @Autowired
    RHardSoftSkill rHardSoftSkill;
    
    public List<HardSoftSkill> list(){
        return rHardSoftSkill.findAll();
    }
    
    public Optional<HardSoftSkill> getOne(int id){
        return rHardSoftSkill.findById(id);
    }
    
    public Optional<HardSoftSkill> getByNombre(String nombre){
        return rHardSoftSkill.findByNombre(nombre);
    }
    
    public void save(HardSoftSkill skill){
        rHardSoftSkill.save(skill);
    }
    
    public void delete(int id){
        rHardSoftSkill.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHardSoftSkill.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rHardSoftSkill.existsByNombre(nombre);
    }
}
