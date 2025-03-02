package com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO;

import java.math.BigDecimal;
import java.util.Set;

import com.medicalsoftcontable.medicalsoftcontable.enums.TipoCuenta;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaContableRequestDTO {

    @NotNull
    @NotEmpty(message = "El código de cuenta no puede estar vacío")
    private String codigoCuenta;

    @NotNull
    @NotEmpty(message = "El nombre de la cuenta es obligatorio")
    private String nombreCuenta;

    private TipoCuenta tipoCuenta;

    @NotNull
    @Min(1)
    private int usuarioId;

    @NotNull
    @Positive(message = "El saldo inicial debe ser mayor a 0")
    private BigDecimal saldoInicial;

    private Boolean estado;
    
    private Set<DetalleAsientoDTO> detallesAsientos;

    private Long facturaId;
}
