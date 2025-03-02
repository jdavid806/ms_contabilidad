package com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TerceroResponseDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String identificacion;
    private List<FacturaResponseDTO> facturas;
}
