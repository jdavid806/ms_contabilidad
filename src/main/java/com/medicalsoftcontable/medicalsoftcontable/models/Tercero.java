package com.medicalsoftcontable.medicalsoftcontable.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoTerceros;

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
@Table(name = "terceros")
@Getter
@Setter
@NoArgsConstructor
public class Tercero extends BaseModel {

  @NotNull
  @NotEmpty
  private String nombre;

  @NotNull
  @NotEmpty
  private String apellidos;

  @NotNull
  private TipoTerceros tipo;

  @NotEmpty
  @NotNull
  private String identificacion;

  @NotEmpty
  @NotNull
  private String numeroIdentificacion;

  @NotNull
  @NotEmpty
  private String telefonoContacto;

  @NotNull
  @NotEmpty
  private String direccion;

  @NotNull
  @NotEmpty
  private String correoContacto;

  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private Boolean responsableIVA;

  @NotNull
  @NotEmpty
  private String ciudad;

@OneToMany(mappedBy = "tercero", cascade = CascadeType.ALL)
@JsonManagedReference  // Indica el lado principal de la relación
private List<Factura> facturas;

}
