package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoDetalles;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalles_asientos")
@Getter
@Setter
@NoArgsConstructor
public class DetalleAsiento extends BaseModel{

  private BigDecimal monto;

  private TipoDetalles tipo;
}
