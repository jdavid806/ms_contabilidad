package com.medicalsoftcontable.medicalsoftcontable.models;

import java.util.List;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoTerceros;

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
  private TipoTerceros tipo;

  @NotNull
  @NotEmpty
  private String identificacion;

  @NotNull
  @NotEmpty
  private String telefonoContacto;

  @NotNull
  @NotEmpty
  private String correoContacto;

  @OneToMany(mappedBy = "tercero_id", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Factura> facturas;

}
