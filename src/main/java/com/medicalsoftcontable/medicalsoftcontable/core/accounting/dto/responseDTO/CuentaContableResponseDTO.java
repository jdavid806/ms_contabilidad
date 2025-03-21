package com.medicalsoftcontable.medicalsoftcontable.core.accounting.dto.responseDTO;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.dto.requestDTO.DetalleAsientoRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.enums.TipoCuenta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuentaContableResponseDTO {
    private Long id;
    private String codigoCuenta;
    private String nombreCuenta;
    private TipoCuenta tipoCuenta; 
    private int usuarioId; 
    private BigDecimal saldoInicial; 
    private Boolean estado;
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<DetalleAsientoRequestDTO> detallesAsientos;
}
