package com.medicalsoftcontable.medicalsoftcontable.core.accounting.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
  @Column(unique = true, nullable = false)
  private String numeroAsiento;

  @NotNull
  @Column(nullable = false)
  private Date fechaAsiento;

  private String descripcion;

  @NotNull
  @Column(nullable = false)
  private BigDecimal totalDebe;

  @NotNull  
  @Column(nullable = false)
  private BigDecimal totalHaber;

  private String estado;

  private int usuarioId;

  @OneToMany(mappedBy = "asientoContable", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DetalleAsiento> detallesAsientos = new ArrayList<>();

  @OneToMany(mappedBy = "asientoContable", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LibroContable> librosContables = new ArrayList<>();

}

