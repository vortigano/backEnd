package com.porfolio.cam.Repository;

import com.porfolio.cam.Entity.HardSoftSkill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RHardSoftSkill extends JpaRepository<HardSoftSkill, Integer>{
    Optional<HardSoftSkill>  findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
