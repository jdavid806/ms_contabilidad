package com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO;

import java.math.BigDecimal;

import com.medicalsoftcontable.medicalsoftcontable.enums.TipoDetalles;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleAsientoRequestDTO {

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    @NotNull(message = "El tipo de movimiento es obligatorio")
    private TipoDetalles tipo; // "DEBE" o "HABER"

    @NotNull(message = "Debe especificar una cuenta contable")
    private Long cuentaContableId; 
}
