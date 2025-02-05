package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.enums.EstadosFactura;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "facturas")
@Getter
@Setter
@NoArgsConstructor
public class Factura extends BaseModel {

  @NotNull
  @NotEmpty
  private String numeroFactura;

  @NotNull
  @NotEmpty
  private Date fecha;

  @NotNull
  @NotEmpty
  private BigDecimal total;

  private EstadosFactura estado;

  @ManyToOne
  @JoinColumn(name = "tercero_id")
  private Tercero tercero_id;

  @OneToMany(mappedBy = "factura_id", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CuentaContable> cuentaContables;

}
