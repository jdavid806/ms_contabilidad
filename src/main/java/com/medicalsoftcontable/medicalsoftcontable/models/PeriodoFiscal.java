package com.medicalsoftcontable.medicalsoftcontable.models;

import java.util.ArrayList;
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
@Table(name = "periodos_fiscales")
@Getter
@Setter
@NoArgsConstructor
/**
 * Nota aca los parametros "año y mesInicio" crean como int
 * ya que para mayor comodidad se guardan por separado
 * en vez de crear tipos date solo para guardar un numero de maximo 4 caracteres
 */
public class PeriodoFiscal extends BaseModel {

  @NotNull
  @NotEmpty
  private int anio;

  @NotNull
  @NotEmpty
  private int mesInicio;

  @NotNull
  @NotEmpty
  private int mesFinal;

  private String estado;

 @OneToMany(mappedBy = "periodoFiscal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LibroContable> librosContables = new ArrayList<>();

}
