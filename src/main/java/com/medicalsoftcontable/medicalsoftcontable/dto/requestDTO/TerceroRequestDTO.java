package com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO;

import java.util.List;

import com.medicalsoftcontable.medicalsoftcontable.enums.TipoTerceros;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerceroRequestDTO {
    private Long id;
    @NotNull
    @NotEmpty(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull
    @NotEmpty(message = "El apellido es obligatorio")
    private String apellidos;

    @NotNull(message = "El tipo solo puede ser CLIENTE o PROVEEDOR")
    private TipoTerceros tipo;

    @NotNull
    @NotEmpty(message = "La identificación es obligatoria")
    private String identificacion;
    
    @NotNull
    @NotEmpty(message = "El número de identificación es obligatorio")
    private String numeroIdentificacion;
    
    
    @NotNull (message="Debe definir si es responsalbe de IVA")
    private boolean responsableIVA;

    private String telefonoContacto;
    private String correoContacto;
    private String direccion;
    private String ciudad;
    private List<FacturaRequestDTO> facturas;
}
