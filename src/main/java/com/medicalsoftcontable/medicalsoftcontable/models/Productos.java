package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.enums.TiposProducto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Productos extends BaseModel {

    @NotNull(message = "El tipo de producto es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TiposProducto tipoProducto;

    @NotBlank(message = "El código es obligatorio")
    @Column(unique = true, nullable = false)
    private String codigo;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre; 

    @NotNull(message = "Debe especificar si el IVA está incluido")
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean IVAIncluido = false;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "producto_impuesto",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "impuesto_id")
    )
    private List<Impuestos> impuestos = new ArrayList<>();
}

