package com.medicalsoftcontable.medicalsoftcontable.core.accounting.dto.responseDTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleAsientoResponseDTO {
    private Long id;
    private BigDecimal monto;
    private String tipo;
    private Long cuentaContableId;
}