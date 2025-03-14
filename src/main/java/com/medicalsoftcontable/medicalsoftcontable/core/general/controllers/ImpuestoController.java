package com.medicalsoftcontable.medicalsoftcontable.core.general.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalsoftcontable.medicalsoftcontable.core.general.dto.requestDTO.ImpuestoRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.core.general.dto.responseDTO.ApiResponse;
import com.medicalsoftcontable.medicalsoftcontable.core.general.models.Impuestos;
import com.medicalsoftcontable.medicalsoftcontable.core.general.service.ImpuestosService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("taxes")
public class ImpuestoController {

    @Autowired
    private ImpuestosService impuestosService;

    @PostMapping
    public ResponseEntity<ApiResponse<Impuestos>> createImpuesto(@Valid @RequestBody ImpuestoRequestDTO impuestoRequestDTO) {
        Impuestos impuesto = impuestosService.createImpuesto(impuestoRequestDTO);
        ApiResponse<Impuestos> response = new ApiResponse<>(
                true,
                "Impuesto creado con éxito",
                HttpStatus.CREATED.value(),
                impuesto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllImpuestos() {
        List<Impuestos> impuestos = impuestosService.findAll();
        ApiResponse<?> response = new ApiResponse<>(true, "Lista de impuestos obtenida", HttpStatus.OK.value(), impuestos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getImpuestoById(@PathVariable("id") Long id) {
        Impuestos impuesto = impuestosService.findImpuestoById(id);
        ApiResponse<?> response = new ApiResponse<>(true, "Impuesto encontrado", HttpStatus.OK.value(), impuesto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Impuestos>> updateImpuestos(@PathVariable("id") Long id,
            @Valid @RequestBody ImpuestoRequestDTO impuestosRequestDTO) {
        impuestosRequestDTO.setId(id);
        Impuestos impuesto = impuestosService.updateImpuesto(impuestosRequestDTO);
        ApiResponse<Impuestos> response = new ApiResponse<>(true, "Impuesto actualizado con éxito", HttpStatus.OK.value(),
                impuesto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteImpuesto(@PathVariable("id") Long id) {
        impuestosService.deleteImpuesto(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Impuesto eliminado con éxito",
                HttpStatus.NO_CONTENT.value(), "Impuesto con ID " + id + " ha sido eliminado.");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

}
