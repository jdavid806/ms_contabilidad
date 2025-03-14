package com.medicalsoftcontable.medicalsoftcontable.core.invoice.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medicalsoftcontable.medicalsoftcontable.base.BaseModel;
import com.medicalsoftcontable.medicalsoftcontable.core.invoice.enums.EstadoFactura;
import com.medicalsoftcontable.medicalsoftcontable.core.invoice.enums.TipoFactura;
import com.medicalsoftcontable.medicalsoftcontable.core.third.models.Tercero;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "facturas")
@Getter
@Setter
@NoArgsConstructor
public class Factura extends BaseModel {

  @NotNull(message = "El número de factura es obligatorio")
  @Column(unique = true, nullable = false)
  private String numeroFactura;

  @NotNull(message = "La fecha es obligatoria")
  @Column(nullable = false)
  private Date fecha;


  @DecimalMin(value = "0.00", message = "El subtotal no puede ser negativo")
  @Column(nullable = false)
  private BigDecimal subtotal; 


  @DecimalMin(value = "0.00", message = "El total de impuestos no puede ser negativo")
  @Column(nullable = false)
  private BigDecimal impuestos; 
  @NotNull(message = "El total es obligatorio")
  @DecimalMin(value = "0.00", message = "El total no puede ser negativo")
  @Column(nullable = false)
  private BigDecimal total; 

  @NotNull(message = "El tipo de factura es obligatorio")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TipoFactura tipoFactura; 

  @ManyToOne
@JoinColumn(name = "tercero_id")
@JsonBackReference  // Evita que Factura serialice el Tercero nuevamente
private Tercero tercero;

  @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DetallesFactura> detalles = new ArrayList<>();

  @NotNull(message = "El estado de la factura es obligatorio")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EstadoFactura estado;
}

