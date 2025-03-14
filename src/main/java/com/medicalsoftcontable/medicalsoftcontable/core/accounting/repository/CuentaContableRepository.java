package com.medicalsoftcontable.medicalsoftcontable.core.accounting.repository;

import java.util.Optional;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseRepository;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.models.CuentaContable;



public interface CuentaContableRepository extends BaseRepository<CuentaContable> {

    Optional<CuentaContable> findByCodigoCuenta(String codigoCuenta);
    Optional<CuentaContable> findByNombreCuenta(String nombreCuenta);
}
