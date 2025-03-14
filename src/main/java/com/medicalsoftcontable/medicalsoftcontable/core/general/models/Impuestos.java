package com.medicalsoftcontable.medicalsoftcontable.core.general.models;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "impuestos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Impuestos extends BaseModel {

 @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "pais", nullable = false, length = 100)
    private String pais;

    @Column(name = "porcentaje", nullable = false)
    private Double porcentaje;

}
