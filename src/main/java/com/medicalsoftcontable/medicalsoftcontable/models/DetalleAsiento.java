package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoDetalles;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalles_asientos")
@Getter
@Setter
@NoArgsConstructor
public class DetalleAsiento extends BaseModel {

  @NotNull
  @NotEmpty
  private BigDecimal monto;

  private TipoDetalles tipo;

  @ManyToOne
  @JoinColumn(name = "cuenta_contable")
  private CuentaContable cuenta_contable;

  @ManyToOne
  @JoinColumn(name = "asiento_contable")
  private AsientoContable asiento_contable;

}
