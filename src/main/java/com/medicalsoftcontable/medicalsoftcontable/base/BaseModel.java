package com.medicalsoftcontable.medicalsoftcontable.base;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(updatable = false)
  private Instant createdAt;

  private Instant updatedAt;

  private Boolean active;

  @PrePersist
  public void onCreate() {
    createdAt = Instant.now();
    active = true;
  }

  @PreUpdate
  public void onUpdate() {
    updatedAt = Instant.now();
  }

}
