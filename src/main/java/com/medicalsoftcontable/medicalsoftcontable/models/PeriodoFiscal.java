package com.medicalsoftcontable.medicalsoftcontable.models;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class PeriodoFiscal extends BaseModel{

  private int anio;

  private int mesInicio;

  private String estado;
  
}
