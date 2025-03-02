package com.medicalsoftcontable.medicalsoftcontable.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseService;
import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.ImpuestoRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.models.Impuestos;
import com.medicalsoftcontable.medicalsoftcontable.repository.ImpuestoRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ImpuestosService extends BaseService<Impuestos> {

    public ImpuestosService(ImpuestoRepository impuestoRepository) {
        super(impuestoRepository);
    }

    public Impuestos createImpuesto(ImpuestoRequestDTO impuestoRequestDTO) {
        Impuestos impuesto = new Impuestos();
        impuesto.setNombre(impuestoRequestDTO.getNombre());
        impuesto.setDescripcion(impuestoRequestDTO.getDescripcion());
        impuesto.setPais(impuestoRequestDTO.getPais());
        impuesto.setPorcentaje(impuestoRequestDTO.getPorcentaje());
        return save(impuesto);
    }

   
    public Impuestos updateImpuesto(ImpuestoRequestDTO impuestoRequestDTO) {
        Impuestos impuesto = findById(impuestoRequestDTO.getId())
                .orElseThrow(() -> new RuntimeException("Impuesto no encontrado"));

        impuesto.setNombre(impuestoRequestDTO.getNombre());
        impuesto.setDescripcion(impuestoRequestDTO.getDescripcion());
        impuesto.setPais(impuestoRequestDTO.getPais());
        impuesto.setPorcentaje(impuestoRequestDTO.getPorcentaje());
        return save(impuesto);
    }

 
    public void deleteImpuesto(Long id) {
        Impuestos impuesto = findById(id)
                .orElseThrow(() -> new RuntimeException("Impuesto no encontrado"));
        delete(impuesto);
    }

    public List<Impuestos> findAllImpuestos() {
        return findAll();
    }

    public Impuestos findImpuestoById(Long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("Impuesto no encontrado"));
    }
}

