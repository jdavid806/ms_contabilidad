package com.medicalsoftcontable.medicalsoftcontable.models;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "tiposDocumento")
public class TiposDocumento extends BaseModel {

     @Column(name = "abreviacion", nullable = false, length = 100)
    private String nombre;

    @Column(name = "nombreDocumento", length = 255)
    private String nombreDocumento;

    @Column(name = "pais", nullable = false, length = 100)
    private String pais;

}
