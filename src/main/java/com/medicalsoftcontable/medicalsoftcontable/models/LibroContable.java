package com.medicalsoftcontable.medicalsoftcontable.models;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libros_contables")
@Getter
@Setter
@NoArgsConstructor
public class LibroContable extends BaseModel{
  
  private String nombreLibro;

  private String descripcion;

}
