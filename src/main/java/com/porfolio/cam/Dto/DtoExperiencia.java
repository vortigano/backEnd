/**
 * Se replican los mismos campos de entity menos el id
 */
package com.porfolio.cam.Dto;

import javax.validation.constraints.NotBlank;

public class DtoExperiencia {
    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
    
}
