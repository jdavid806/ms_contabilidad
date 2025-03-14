package com.medicalsoftcontable.medicalsoftcontable.core.accounting.models;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libros_contables")
@Getter
@Setter
@NoArgsConstructor
public class LibroContable extends BaseModel {

  @NotNull
  @Column(nullable = false)
  private String nombreLibro;

  private String descripcion;

  @ManyToOne
  @JoinColumn(name = "asiento_contable_id", nullable = false)
  private AsientoContable asientoContable;

  @ManyToOne
  @JoinColumn(name = "periodo_fiscal_id", nullable = false)
  private PeriodoFiscal periodoFiscal;
}