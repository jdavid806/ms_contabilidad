package com.medicalsoftcontable.medicalsoftcontable.core.inventory.models;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "inventarios")
@Getter
@Setter
@NoArgsConstructor
public class Inventario extends BaseModel {
    
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos producto;

    @NotNull(message = "La cantidad en stock es obligatoria")
    @Min(value = 0, message = "La cantidad en stock no puede ser negativa")
    private Integer cantidad;

    @NotNull(message = "El precio de compra es obligatorio")
    @DecimalMin(value = "0.00", message = "El precio de compra no puede ser negativo")
    private BigDecimal precioCompra;

    @Column(length = 255)
    private String ubicacion;

    @Column(length = 100)
    private String lote;

    private LocalDate fechaVencimiento;

    @NotNull(message = "La cantidad disponible es obligatoria")
    @Min(value = 0, message = "La cantidad disponible no puede ser negativa")
    private Integer cantidadDisponible;
}

