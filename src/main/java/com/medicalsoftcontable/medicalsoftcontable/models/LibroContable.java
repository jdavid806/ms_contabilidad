package com.medicalsoftcontable.medicalsoftcontable.models;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

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
@Table(name = "libros_contables")
@Getter
@Setter
@NoArgsConstructor
public class LibroContable extends BaseModel{
  
  @NotNull
  @NotEmpty
  private String nombreLibro;

  private String descripcion;

  @ManyToOne
  @JoinColumn(name = "asiento_id")
  private AsientoContable asiento_id;

  @ManyToOne
  @JoinColumn(name = "perido_id")
  private PeriodoFiscal perido_id;

}
