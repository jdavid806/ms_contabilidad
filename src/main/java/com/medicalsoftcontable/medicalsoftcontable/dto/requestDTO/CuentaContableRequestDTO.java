package com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoCuenta;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Debe especificar el tipo de cuenta: INGRESOS(1), EGRESOS(2), ACTIVOS(3), PASIVOS(4), PATRIMONIO(5), AJUSTES(6)")
    private TipoCuenta tipoCuenta;

    @NotNull(message = "El usuario es obligatorio")
    @Min(value = 1, message = "El ID del usuario debe ser mayor a 0")
    private int usuarioId;

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.00", message = "El saldo inicial no puede ser negativo")
    private BigDecimal saldoInicial;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<DetalleAsientoRequestDTO> detallesAsientos;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long facturaId;
}

