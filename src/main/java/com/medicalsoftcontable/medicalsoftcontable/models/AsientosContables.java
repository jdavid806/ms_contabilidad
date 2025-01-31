package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;
import java.util.Date;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "asientos_contables")
@Getter
@Setter
@NoArgsConstructor
public class AsientosContables extends BaseModel {
  
  private String numeroAsiento;

  private Date fechaAsiento;

  private String descripcion;

  private BigDecimal totalDebe;
  
  private BigDecimal totalHaber;

  private String estado;

  private int usuarioId;
  
}
