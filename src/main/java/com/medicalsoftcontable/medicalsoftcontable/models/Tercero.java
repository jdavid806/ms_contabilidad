package com.medicalsoftcontable.medicalsoftcontable.models;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoTerceros;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "terceros")
@Getter
@Setter
@NoArgsConstructor
public class Tercero extends BaseModel{
  
  private String nombre;

  private TipoTerceros tipo;

  private String identificacion;

  private String telefonoContacto;

  private String correoContacto;

}
