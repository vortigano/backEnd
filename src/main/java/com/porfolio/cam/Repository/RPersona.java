package com.porfolio.cam.Repository;

import com.porfolio.cam.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPersona extends JpaRepository<Persona, Long> {
    
}
