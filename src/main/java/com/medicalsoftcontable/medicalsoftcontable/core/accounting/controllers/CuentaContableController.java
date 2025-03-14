package com.medicalsoftcontable.medicalsoftcontable.core.accounting.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.medicalsoftcontable.medicalsoftcontable.core.accounting.dto.requestDTO.CuentaContableRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.dto.responseDTO.CuentaContableResponseDTO;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.models.CuentaContable;
import com.medicalsoftcontable.medicalsoftcontable.core.accounting.services.CuentaContableServices;
import com.medicalsoftcontable.medicalsoftcontable.core.general.dto.responseDTO.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("accounting-account")
@RequiredArgsConstructor
public class CuentaContableController {

    private final CuentaContableServices cuentaContableServices;

    // Crear una nueva cuenta contable
    @PostMapping
    public ResponseEntity<ApiResponse<CuentaContableResponseDTO>> createCuentaContable(
            @Valid @RequestBody CuentaContableRequestDTO requestDTO) {
        CuentaContable nuevaCuenta = cuentaContableServices.createCuentaContable(requestDTO);
        CuentaContableResponseDTO responseDTO = new CuentaContableResponseDTO(
                nuevaCuenta.getId(),
                nuevaCuenta.getCodigoCuenta(),
                nuevaCuenta.getNombreCuenta(),
                nuevaCuenta.getTipoCuenta(),
                nuevaCuenta.getUsuarioId(),
                nuevaCuenta.getSaldoInicial(),
                nuevaCuenta.getEstado(),
                null // Por ahora, sin detalles de asiento
        );

        ApiResponse<CuentaContableResponseDTO> response = new ApiResponse<>(
                true,
                "Cuenta contable creada exitosamente",
                HttpStatus.CREATED.value(),
                responseDTO
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Obtener todas las cuentas contables
    @GetMapping
    public ResponseEntity<ApiResponse<List<CuentaContableResponseDTO>>> getAllCuentas() {
        List<CuentaContable> cuentas = cuentaContableServices.findAll();
        List<CuentaContableResponseDTO> responseDTOs = cuentas.stream()
                .map(cuenta -> new CuentaContableResponseDTO(
                        cuenta.getId(),
                        cuenta.getCodigoCuenta(),
                        cuenta.getNombreCuenta(),
                        cuenta.getTipoCuenta(),
                        cuenta.getUsuarioId(),
                        cuenta.getSaldoInicial(),
                        cuenta.getEstado(),
                        null
                ))
                .collect(Collectors.toList());

        ApiResponse<List<CuentaContableResponseDTO>> response = new ApiResponse<>(
                true,
                "Lista de cuentas contables obtenida correctamente",
                HttpStatus.OK.value(),
                responseDTOs
        );

        return ResponseEntity.ok(response);
    }

    // Obtener una cuenta contable por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CuentaContableResponseDTO>> getCuentaById(@PathVariable Long id) {
        CuentaContable cuenta = cuentaContableServices.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta contable no encontrada"));

        CuentaContableResponseDTO responseDTO = new CuentaContableResponseDTO(
                cuenta.getId(),
                cuenta.getCodigoCuenta(),
                cuenta.getNombreCuenta(),
                cuenta.getTipoCuenta(),
                cuenta.getUsuarioId(),
                cuenta.getSaldoInicial(),
                cuenta.getEstado(),
                null
        );

        ApiResponse<CuentaContableResponseDTO> response = new ApiResponse<>(
                true,
                "Cuenta contable encontrada",
                HttpStatus.OK.value(),
                responseDTO
        );

        return ResponseEntity.ok(response);
    }
}
