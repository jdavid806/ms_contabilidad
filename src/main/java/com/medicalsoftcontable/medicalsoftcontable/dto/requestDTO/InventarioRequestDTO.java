package com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventarioRequestDTO {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que 0")
    private Integer cantidad;

    @NotBlank(message = "El lote no puede estar vacío")
    @Size(max = 100, message = "El lote no puede tener más de 100 caracteres")
    private String lote;

    private String ubicacion;    

    @Future(message = "La fecha de vencimiento debe ser en el futuro")
    private LocalDate fechaVencimiento;

    private BigDecimal costoUnitario;  // Opcional, si manejas costo de inventario
}
