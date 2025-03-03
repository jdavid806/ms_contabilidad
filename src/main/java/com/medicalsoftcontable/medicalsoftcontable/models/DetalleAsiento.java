package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoDetalles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
  @Column(nullable = false)
  private BigDecimal monto;

  @Enumerated(EnumType.STRING)
  private TipoDetalles tipo;

  @ManyToOne
  @JoinColumn(name = "cuenta_contable_id", nullable = false)
  private CuentaContable cuentaContable;

  @ManyToOne
  @JoinColumn(name = "asiento_contable_id", nullable = false)
  private AsientoContable asientoContable;

}
