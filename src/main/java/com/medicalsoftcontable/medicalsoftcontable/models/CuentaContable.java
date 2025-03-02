package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoCuenta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cuentas_contables")
@Getter
@Setter
@NoArgsConstructor
public class CuentaContable extends BaseModel {

    @NotNull(message = "El código de cuenta es obligatorio")
    @Column(unique = true, nullable = false)
    private String codigoCuenta;

    @NotNull(message = "El nombre de la cuenta es obligatorio")
    @Column(nullable = false)
    private String nombreCuenta;

    @NotNull(message = "El tipo de cuenta es obligatorio")
    @Column(nullable = false)
    private TipoCuenta tipoCuenta; // Enum: ACTIVO, PASIVO, PATRIMONIO, INGRESO, GASTO, AJUSTES

    //Agregar tipo de cuenta (deudora, transaccionales, acredora, impuesto)

    @NotNull(message = "El usuario es obligatorio")
    @Min(value = 1, message = "El ID del usuario debe ser mayor a 0")
    @Column(nullable = false)
    private int usuarioId;

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.00", message = "El saldo inicial no puede ser negativo")
    @Column(nullable = false)
    private BigDecimal saldoInicial;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @OneToMany(mappedBy = "cuentaContable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleAsiento> detallesAsientos = new ArrayList<>();

    // @OneToMany(mappedBy = "cuentaContable", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Set<Factura> facturas;
    

}

