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
@Table(name = "facturas")
@Getter
@Setter
@NoArgsConstructor
public class Factura extends BaseModel{

  private String numeroFactura;

  private Date fecha;
  
  private BigDecimal total;

  private String estado;
  
}
