package com.medicalsoftcontable.medicalsoftcontable.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseService;
import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.TerceroRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO.FacturaResponseDTO;
import com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO.TerceroResponseDTO;
import com.medicalsoftcontable.medicalsoftcontable.models.Tercero;
import com.medicalsoftcontable.medicalsoftcontable.repository.TerceroRepository;

import jakarta.transaction.Transactional;

@Service
public class TerceroService extends BaseService<Tercero> {

    public TerceroService(TerceroRepository terceroRepository) {
        super(terceroRepository);
    }

    @Transactional
    public TerceroResponseDTO crearTercero(TerceroRequestDTO terceroRequestDTO) {
        Tercero tercero = new Tercero();
        tercero.setNombre(terceroRequestDTO.getNombre());
        tercero.setApellidos(terceroRequestDTO.getApellidos());
        tercero.setTipo(terceroRequestDTO.getTipo());
        tercero.setIdentificacion(terceroRequestDTO.getIdentificacion());
        tercero.setNumeroIdentificacion(terceroRequestDTO.getNumeroIdentificacion());
        tercero.setTelefonoContacto(terceroRequestDTO.getTelefonoContacto());
        tercero.setCorreoContacto(terceroRequestDTO.getCorreoContacto());
        tercero.setDireccion(terceroRequestDTO.getDireccion());
        tercero.setCiudad(terceroRequestDTO.getCiudad());
        tercero.setResponsableIVA(terceroRequestDTO.isResponsableIVA());

        tercero = save(tercero); // Guardamos el tercero y obtenemos la entidad persistida

        return convertirATerceroDTO(tercero); // Retornamos el DTO en vez de la entidad
    }

    @Transactional
    public TerceroResponseDTO updateTercero(TerceroRequestDTO terceroRequestDTO) {
        Tercero tercero = findById(terceroRequestDTO.getId())
                .orElseThrow(() -> new RuntimeException("Tercero no encontrado"));

        tercero.setNombre(terceroRequestDTO.getNombre());
        tercero.setApellidos(terceroRequestDTO.getApellidos());
        tercero.setTipo(terceroRequestDTO.getTipo());
        tercero.setIdentificacion(terceroRequestDTO.getIdentificacion());
        tercero.setTelefonoContacto(terceroRequestDTO.getTelefonoContacto());
        tercero.setCorreoContacto(terceroRequestDTO.getCorreoContacto());
        tercero.setDireccion(terceroRequestDTO.getDireccion());
        tercero.setCiudad(terceroRequestDTO.getCiudad());

        tercero = save(tercero); // Guardamos los cambios

        return convertirATerceroDTO(tercero); // Retornamos el DTO en vez de la entidad
    }

    @Transactional
    public void deleteTercero(Long id) {
        Tercero tercero = findById(id)
                .orElseThrow(() -> new RuntimeException("Tercero no encontrado"));
        delete(tercero);
    }
    

    public List<TerceroResponseDTO> findAllTerceros() {
        return findAll().stream()
                .map(this::convertirATerceroDTO)
                .collect(Collectors.toList());
    }

    public TerceroResponseDTO findTerceroById(Long id) {
        Tercero tercero = findById(id)
                .orElseThrow(() -> new RuntimeException("Tercero no encontrado"));
        return convertirATerceroDTO(tercero);
    }

    private TerceroResponseDTO convertirATerceroDTO(Tercero tercero) {
        List<FacturaResponseDTO> facturasDTO = Optional.ofNullable(tercero.getFacturas())
        .orElse(Collections.emptyList())
        .stream()
        .map(factura -> new FacturaResponseDTO(
                factura.getId(),
                factura.getNumeroFactura(),
                factura.getTotal(),
                factura.getTipoFactura().name()))
        .collect(Collectors.toList());


        return new TerceroResponseDTO(
            tercero.getId(),
            tercero.getNombre(),
            tercero.getApellidos(),
            tercero.getIdentificacion(),
            tercero.getResponsableIVA(),
            tercero.getNumeroIdentificacion(),
            tercero.getTelefonoContacto(),
            tercero.getCorreoContacto(),
            tercero.getDireccion(),
            tercero.getCiudad(),
            facturasDTO
        );
        
}
}
