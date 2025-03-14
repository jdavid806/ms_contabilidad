package com.medicalsoftcontable.medicalsoftcontable.core.general.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TiposDocumentoRequestDTO {
 
 private long id;

 @NotBlank (message="La abreviatura es obligatoria")
 private String abreviatura;

 @NotBlank (message= "El nombre del documento es obligatorio")
 private String nombreDocumento;

 @NotBlank (message="El codigo del país es obligatorio")
 private String Pais;
}
