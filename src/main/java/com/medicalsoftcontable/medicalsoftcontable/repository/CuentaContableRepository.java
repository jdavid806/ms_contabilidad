package com.medicalsoftcontable.medicalsoftcontable.repository;

import java.util.Optional;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseRepository;
import com.medicalsoftcontable.medicalsoftcontable.models.CuentaContable;



public interface CuentaContableRepository extends BaseRepository<CuentaContable> {

    Optional<CuentaContable> findByCodigoCuenta(String codigoCuenta);
}
