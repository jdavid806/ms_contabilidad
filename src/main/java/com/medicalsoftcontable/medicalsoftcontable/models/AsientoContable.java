package com.medicalsoftcontable.medicalsoftcontable.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "asientos_contables")
@Getter
@Setter
@NoArgsConstructor
public class AsientoContable extends BaseModel {

  @NotNull
  @NotEmpty
  private String numeroAsiento;

  @NotNull
  @NotEmpty
  private Date fechaAsiento;

  private String descripcion;

  @NotNull
  @NotEmpty
  private BigDecimal totalDebe;

  @NotNull
  @NotEmpty
  private BigDecimal totalHaber;

  private String estado;

  private int usuarioId;

  @OneToMany(mappedBy = "asiento_contable", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DetalleAsiento> detalles_asientos;

  @OneToMany(mappedBy = "asiento_id", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LibroContable> libros_contables;

}
