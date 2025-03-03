package com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsientoContableResponseDTO {
    private Long id;
    private String numeroAsiento;
    private LocalDate fechaAsiento;
    private String descripcion;
    private BigDecimal totalDebe;
    private BigDecimal totalHaber;
    private String estado;
    private int usuarioId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DetalleAsientoResponseDTO> detalles;
}