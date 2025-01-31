package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cuentas_contables")
@Getter
@Setter
@NoArgsConstructor
public class CuentaContable extends BaseModel{

  private String codigoCuenta;

  private String nombreCuenta;

  private String tipoCuenta;

  private int usuarioId;

  private BigDecimal saldoInicial;

  private Boolean estado;

}
