package com.medicalsoftcontable.medicalsoftcontable.controllers;

import java.util.List;

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

import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.AsientoContableRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO.ApiResponse;
import com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO.AsientoContableResponseDTO;
import com.medicalsoftcontable.medicalsoftcontable.services.AsientoContableService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("accounting-entry")
public class AsientoContableController {

        private final AsientoContableService asientoContableService;

        public AsientoContableController(AsientoContableService asientoContableService) {
                this.asientoContableService = asientoContableService;
        }
     
        @GetMapping
        public ResponseEntity<ApiResponse<List<AsientoContableResponseDTO>>> getAllAsientos() {
                List<AsientoContableResponseDTO> asientos = asientoContableService.findAllAsientos();
                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Asientos contables obtenidos correctamente", 200, asientos));
        }

     
        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<AsientoContableResponseDTO>> getAsientoById(@PathVariable("id") Long id) {
                AsientoContableResponseDTO asiento = asientoContableService.findAsientoById(id);
                return ResponseEntity
                                .ok(new ApiResponse<>(true, "Asiento contable obtenido correctamente", 200, asiento));
        }


        @PostMapping
        public ResponseEntity<ApiResponse<AsientoContableResponseDTO>> createAsiento(
                        @Valid @RequestBody AsientoContableRequestDTO request) {
                AsientoContableResponseDTO newAsiento = asientoContableService.createAsiento(request);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new ApiResponse<>(true, "Asiento contable creado correctamente", 201,
                                                newAsiento));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<AsientoContableResponseDTO>> updateAsiento(
                        @PathVariable Long id,
                        @Valid @RequestBody AsientoContableRequestDTO request) {
                AsientoContableResponseDTO updatedAsiento = asientoContableService.updateAsiento(id, request);
                return ResponseEntity.ok(new ApiResponse<>(true, "Asiento contable actualizado correctamente", 200,
                                updatedAsiento));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteAsiento(@PathVariable ("id")Long id) {
                asientoContableService.deleteAsiento(id);
                return ResponseEntity
                                .ok(new ApiResponse<>(true, "Asiento contable eliminado correctamente", 200, null));
        }
}
