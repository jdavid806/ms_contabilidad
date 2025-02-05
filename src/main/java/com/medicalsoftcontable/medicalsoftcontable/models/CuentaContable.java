package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;
import java.util.Set;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoCuenta;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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

  @NotNull
  @NotEmpty
  private String codigoCuenta;

  @NotNull
  @NotEmpty
  private String nombreCuenta;

  private TipoCuenta tipoCuenta;

  @NotEmpty
  @NotNull
  @Min(1)
  private int usuarioId;

  @NotEmpty
  @NotNull
  private BigDecimal saldoInicial;

  private Boolean estado;

  @OneToMany(mappedBy= "cuenta_contable")
  private Set<DetalleAsiento> detalles_asientos;

  @ManyToOne
  @JoinColumn(name = "factura_id")
  private Factura factura_id;

}
