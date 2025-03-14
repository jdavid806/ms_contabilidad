package com.medicalsoftcontable.medicalsoftcontable.core.general.dto.requestDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ImpuestoRequestDTO {

    private long id;
 
    @NotNull
    @NotEmpty (message="Ingrese el nombre del impuesto")
    private String nombre;
    
    private String descripcion;
    
    @NotEmpty (message="seleccione el país")
    private String pais;

    @NotEmpty (message="Ingrese el porcentaje ej: 2.4")
    private Double porcentaje;


}
