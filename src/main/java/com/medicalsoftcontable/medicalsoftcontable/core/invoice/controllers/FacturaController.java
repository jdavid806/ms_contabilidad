package com.medicalsoftcontable.medicalsoftcontable.core.invoice.controllers;

import com.medicalsoftcontable.medicalsoftcontable.core.general.dto.responseDTO.ApiResponse;
import com.medicalsoftcontable.medicalsoftcontable.core.invoice.dto.requestDTO.FacturaRequestDTO;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalsoftcontable.medicalsoftcontable.core.invoice.services.FacturaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("bill")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<ApiResponse<FacturaRequestDTO>> createFactura(@Valid @RequestBody FacturaRequestDTO facturaRequestDTO) {
        FacturaRequestDTO facturaCreada = facturaService.crearFactura(facturaRequestDTO);
        ApiResponse<FacturaRequestDTO> response = new ApiResponse<>(
                true,
                "Factura creada con éxito",
                HttpStatus.CREATED.value(),
                facturaCreada);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FacturaRequestDTO>>> getAllFacturas() {
        List<FacturaRequestDTO> facturas = facturaService.findAll().stream()
                .map(facturaService::convertirFacturaARequestDTO)
                .collect(Collectors.toList());
        ApiResponse<List<FacturaRequestDTO>> response = new ApiResponse<>(
                true,
                "Lista de facturas obtenida",
                HttpStatus.OK.value(),
                facturas);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<ApiResponse<FacturaRequestDTO>> getFacturaById(@PathVariable("id") Long id) {
    //     Factura factura = facturaService.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    //     FacturaRequestDTO facturaDTO = facturaService.convertirFacturaARequestDTO(factura);
    //     ApiResponse<FacturaRequestDTO> response = new ApiResponse<>(
    //             true,
    //             "Factura encontrada",
    //             HttpStatus.OK.value(),
    //             facturaDTO);
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<ApiResponse<FacturaRequestDTO>> updateFactura(@PathVariable("id") Long id,
    //         @Valid @RequestBody FacturaRequestDTO facturaRequestDTO) {
    //     facturaRequestDTO.setId(id);
    //     FacturaRequestDTO facturaActualizada = facturaService.actualizarFactura(facturaRequestDTO);
    //     ApiResponse<FacturaRequestDTO> response = new ApiResponse<>(true, "Factura actualizada con éxito", HttpStatus.OK.value(),
    //             facturaActualizada);
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteFactura(@PathVariable("id") Long id) {
        facturaService.deleteById(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Factura eliminada con éxito",
                HttpStatus.NO_CONTENT.value(), "Factura con ID " + id + " ha sido eliminada.");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
