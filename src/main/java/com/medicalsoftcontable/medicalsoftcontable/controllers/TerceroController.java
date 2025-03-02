package com.medicalsoftcontable.medicalsoftcontable.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.TerceroRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO.ApiResponse;
import com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO.TerceroResponseDTO;
import com.medicalsoftcontable.medicalsoftcontable.services.TerceroService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("Third-Party")
@RequiredArgsConstructor
@Validated
public class TerceroController {

    private final TerceroService terceroService;

    @PostMapping
    public ResponseEntity<ApiResponse<TerceroResponseDTO>> createTercero(@Valid @RequestBody TerceroRequestDTO terceroRequestDTO) {
        TerceroResponseDTO tercero = terceroService.crearTercero(terceroRequestDTO);
        ApiResponse<TerceroResponseDTO> response = new ApiResponse<>(
                true,
                "Tercero creado con éxito",
                HttpStatus.CREATED.value(),
                tercero);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getTerceros(@RequestParam(name = "id", required = false) Long id) {
        ApiResponse<?> response;
        if (id != null) {          
            TerceroResponseDTO tercero = terceroService.findTerceroById(id);
            response = new ApiResponse<>(true, "Tercero encontrado", HttpStatus.OK.value(), tercero);
        } else {           
            List<TerceroResponseDTO> terceros = terceroService.findAllTerceros();
            response = new ApiResponse<>(true, "Lista de terceros obtenida", HttpStatus.OK.value(), terceros);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TerceroResponseDTO>> updateTercero(@PathVariable("id") Long id,
            @Valid @RequestBody TerceroRequestDTO terceroRequestDTO) {
        terceroRequestDTO.setId(id);
        TerceroResponseDTO tercero = terceroService.updateTercero(terceroRequestDTO);
        ApiResponse<TerceroResponseDTO> response = new ApiResponse<>(true, "Tercero actualizado con éxito", HttpStatus.OK.value(),
                tercero);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTercero(@PathVariable Long id) {
        terceroService.deleteTercero(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Tercero eliminado con éxito", HttpStatus.OK.value(), null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
