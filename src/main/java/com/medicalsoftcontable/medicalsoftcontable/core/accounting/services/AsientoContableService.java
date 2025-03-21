package com.medicalsoftcontable.medicalsoftcontable.core.accounting.services;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseRepository;
import com.medicalsoftcontable.medicalsoftcontable.base.BaseService;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.dto.requestDTO.AsientoContableRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.dto.responseDTO.AsientoContableResponseDTO;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.dto.responseDTO.DetalleAsientoResponseDTO;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.models.AsientoContable;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.repository.AsientoContableRepository;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.repository.CuentaContableRepository;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.repository.DetalleAsientoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AsientoContableService extends BaseService<AsientoContable> {

    private final AsientoContableRepository asientoContableRepository;
 

    public AsientoContableService(
        BaseRepository<AsientoContable> baseRepository, 
        AsientoContableRepository asientoContableRepository,
        CuentaContableRepository cuentaContableRepository,
        DetalleAsientoRepository detalleAsientoRepository
    ) {
        super(baseRepository);
        this.asientoContableRepository = asientoContableRepository;
     
    }
    

    public AsientoContableResponseDTO createAsiento(AsientoContableRequestDTO request) {
        AsientoContable asiento = new AsientoContable();
        asiento.setNumeroAsiento(request.getNumeroAsiento());
        asiento.setFechaAsiento(request.getFechaAsiento());
        asiento.setDescripcion(request.getDescripcion());
        asiento.setTotalDebe(request.getTotalDebe());
        asiento.setTotalHaber(request.getTotalHaber());
        asiento.setEstado(request.getEstado());
    
        AsientoContable savedAsiento = asientoContableRepository.save(asiento);
        return convertirAResponseDTO(savedAsiento);
    }
    

    public AsientoContableResponseDTO updateAsiento(Long id, AsientoContableRequestDTO request) {
        AsientoContable asiento = asientoContableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asiento contable no encontrado"));

        asiento.setNumeroAsiento(request.getNumeroAsiento());
        asiento.setFechaAsiento(request.getFechaAsiento());
        asiento.setDescripcion(request.getDescripcion());
        asiento.setTotalDebe(request.getTotalDebe());
        asiento.setTotalHaber(request.getTotalHaber());
        asiento.setEstado(request.getEstado());

        AsientoContable actualizado = save(asiento);
        return convertirAResponseDTO(actualizado);
    }

    public void deleteAsiento(Long id) {
        AsientoContable asiento = asientoContableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asiento contable no encontrado"));
        delete(asiento);
    }

    public List<AsientoContableResponseDTO> findAllAsientos() {
        return findAll().stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public AsientoContableResponseDTO findAsientoById(Long id) {
        AsientoContable asiento = asientoContableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asiento contable no encontrado"));
        return convertirAResponseDTO(asiento);
    }

    private AsientoContableResponseDTO convertirAResponseDTO(AsientoContable asiento) {
        return new AsientoContableResponseDTO(
            asiento.getId(),
            asiento.getNumeroAsiento(),
            asiento.getFechaAsiento().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(),
            asiento.getDescripcion(),
            asiento.getTotalDebe(),
            asiento.getTotalHaber(),
            asiento.getEstado(),
            asiento.getUsuarioId(),
            asiento.getDetallesAsientos().stream()
                .map(detalle -> new DetalleAsientoResponseDTO(
                    detalle.getId(),
                    detalle.getMonto(),
                    detalle.getTipo().name(), 
                    detalle.getCuentaContable().getId()
                ))
                .collect(Collectors.toList()) 
        );
    } 
    
    
}



