package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "detalles_factura")
@Getter
@Setter
@NoArgsConstructor
public class DetallesFactura extends BaseModel {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Inventario inventario;

    @NotNull
    @Min(value = 1)
    private Integer cantidad;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal precioUnitario;

    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal subtotal;

    @Column(nullable = false)
    private BigDecimal impuesto;

    
}
