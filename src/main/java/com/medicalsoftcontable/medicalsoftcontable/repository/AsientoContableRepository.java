package com.medicalsoftcontable.medicalsoftcontable.repository;

import org.springframework.stereotype.Repository;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseRepository;
import com.medicalsoftcontable.medicalsoftcontable.models.AsientoContable;

@Repository
public interface AsientoContableRepository extends BaseRepository<AsientoContable> {
    // Puedes añadir consultas personalizadas si es necesario
}