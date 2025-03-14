package com.medicalsoftcontable.medicalsoftcontable.core.inventory.controllers;

import com.medicalsoftcontable.medicalsoftcontable.core.general.dto.responseDTO.ApiResponse;
import com.medicalsoftcontable.medicalsoftcontable.core.inventory.dto.requestDTO.InventarioRequestDTO;
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

import com.medicalsoftcontable.medicalsoftcontable.core.inventory.models.Inventario;
import com.medicalsoftcontable.medicalsoftcontable.core.inventory.services.InventarioService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("inventary") 
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Inventario>> createInventario(@Valid @RequestBody InventarioRequestDTO inventarioRequestDTO) {
        Inventario inventario = inventarioService.createInventario(inventarioRequestDTO);
        ApiResponse<Inventario> response = new ApiResponse<>(true, "Inventario registrado con éxito", HttpStatus.CREATED.value(), inventario);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Inventario>>> getAllInventarios() {
        List<Inventario> inventarios = inventarioService.findAllInventarios();
        ApiResponse<List<Inventario>> response = new ApiResponse<>(true, "Lista de inventarios obtenida", HttpStatus.OK.value(), inventarios);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Inventario>> getInventarioById(@PathVariable("id") Long id) {
        Inventario inventario = inventarioService.findInventarioById(id);
        ApiResponse<Inventario> response = new ApiResponse<>(true, "Inventario encontrado", HttpStatus.OK.value(), inventario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Inventario>> updateInventario(
            @PathVariable("id") Long id, 
            @Valid @RequestBody InventarioRequestDTO inventarioRequestDTO) {
        
        Inventario inventario = inventarioService.updateInventario(id, inventarioRequestDTO);
        ApiResponse<Inventario> response = new ApiResponse<>(true, "Inventario actualizado con éxito", HttpStatus.OK.value(), inventario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteInventario(@PathVariable("id") Long id) {
        inventarioService.deleteInventario(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Inventario eliminado con éxito", HttpStatus.NO_CONTENT.value(), "Inventario con ID " + id + " ha sido eliminado.");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

