package com.medicalsoftcontable.medicalsoftcontable.core.accounting.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseRepository;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.models.AsientoContable;

@Repository
public interface AsientoContableRepository extends BaseRepository<AsientoContable> {
    Optional<AsientoContable> findByNumeroAsiento(String numeroAsiento);
}
