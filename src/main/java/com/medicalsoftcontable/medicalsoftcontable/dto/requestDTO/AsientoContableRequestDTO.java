package com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsientoContableRequestDTO {
    @NotNull
    @NotEmpty(message = "El número de asiento no puede estar vacío")
    private String numeroAsiento;

    @NotNull
    private Date fechaAsiento;

    @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
    private String descripcion;

    @NotNull
    @Positive(message = "El total debe ser un número positivo")
    private BigDecimal totalDebe;

    @NotNull
    @Positive(message = "El total debe ser un número positivo")
    private BigDecimal totalHaber;

    private String estado;

    private int usuarioId;

    
}
