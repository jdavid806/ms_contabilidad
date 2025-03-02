package com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.medicalsoftcontable.medicalsoftcontable.enums.EstadoFactura;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoFactura;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaRequestDTO {

    @NotEmpty(message = "El número de factura es obligatorio")
    private String numeroFactura;

    @NotNull(message = "La fecha es obligatoria")
    private Date fecha;

    private BigDecimal subtotal; 
  
    private BigDecimal impuestos; 

   @NotNull(message = "El total es obligatorio")
@DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
private BigDecimal total;


    @NotNull(message = "El tipo de factura es obligatorio")
    private TipoFactura tipoFactura;

    @NotNull(message = "El estado de la factura es obligatorio")
    private EstadoFactura estado;

    @NotNull(message = "Debe definir un tercero para generar la factura")
    private Long terceroId; 

    private List<DetalleFacturaRequestDTO> detalles;
}
