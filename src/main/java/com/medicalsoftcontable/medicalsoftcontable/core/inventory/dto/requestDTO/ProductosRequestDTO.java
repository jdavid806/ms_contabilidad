package com.medicalsoftcontable.medicalsoftcontable.core.inventory.dto.requestDTO;

import java.math.BigDecimal;
import java.util.List;

import com.medicalsoftcontable.medicalsoftcontable.core.inventory.enums.TiposProducto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductosRequestDTO {


    private Long id;
    
    @NotNull(message = "El tipo de producto es obligatorio")
    private TiposProducto tipoProducto;

    @NotBlank(message = "El código es obligatorio (puede incluir letras y números)")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull (message="Debe indicar si el IVA esta incluido")
    private boolean IVAIncluido;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio de venta debe ser mayor que 0")
    private BigDecimal precioVenta1;    

    @DecimalMin(value = "0.00", message = "El precio de venta 2 no puede ser negativo")
    private BigDecimal precioVenta2;

    @DecimalMin(value = "0.00", message = "El precio de compra no puede ser negativo")
    private BigDecimal precioCompra;

    private String codigoBarra;
    private String descripcion;
    private String descripcionLarga;
    private String urlImagen;  

    private boolean inventariable;  
    private String marca;

    private List<Long> impuestosIds; 
}

