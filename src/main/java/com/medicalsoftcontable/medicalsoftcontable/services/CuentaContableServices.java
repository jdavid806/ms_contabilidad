package com.medicalsoftcontable.medicalsoftcontable.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseRepository;
import com.medicalsoftcontable.medicalsoftcontable.base.BaseService;
import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.CuentaContableRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.models.CuentaContable;
import com.medicalsoftcontable.medicalsoftcontable.repository.CuentaContableRepository;
import com.medicalsoftcontable.medicalsoftcontable.repository.DetalleAsientoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CuentaContableServices extends BaseService<CuentaContable> {

    private final CuentaContableRepository cuentaContableRepository;

  
    public CuentaContableServices(
        BaseRepository<CuentaContable> baseRepository, 
        CuentaContableRepository cuentaContableRepository,
        DetalleAsientoRepository detalleAsientoRepository
    ) {
        super(baseRepository);
        this.cuentaContableRepository = cuentaContableRepository;
    }

    public CuentaContable createCuentaContable(CuentaContableRequestDTO cuentaContableRequestDTO) {        
        Optional<CuentaContable> cuentaExistente = cuentaContableRepository.findByCodigoCuenta(cuentaContableRequestDTO.getCodigoCuenta());
        if (cuentaExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe una cuenta contable con este código.");
        }

       
        CuentaContable cuentaContable = new CuentaContable();
        cuentaContable.setCodigoCuenta(cuentaContableRequestDTO.getCodigoCuenta());
        cuentaContable.setNombreCuenta(cuentaContableRequestDTO.getNombreCuenta());
        cuentaContable.setTipoCuenta(cuentaContableRequestDTO.getTipoCuenta());
        cuentaContable.setUsuarioId(cuentaContableRequestDTO.getUsuarioId());
        cuentaContable.setSaldoInicial(cuentaContableRequestDTO.getSaldoInicial());
        cuentaContable.setEstado(cuentaContableRequestDTO.getEstado());

        return save (cuentaContable);
    }       

     
}
