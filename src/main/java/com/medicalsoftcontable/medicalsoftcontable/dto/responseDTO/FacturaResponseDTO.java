package com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FacturaResponseDTO {
    private Long id;
    private String numeroFactura;
    private BigDecimal total;
    private String tipoFactura;
}