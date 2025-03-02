package com.medicalsoftcontable.medicalsoftcontable.controllers;

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

import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.ProductosRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO.ApiResponse;
import com.medicalsoftcontable.medicalsoftcontable.models.Productos;
import com.medicalsoftcontable.medicalsoftcontable.services.ProductoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("product")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    @PostMapping
    public ResponseEntity<ApiResponse<Productos>> createProducto(@Valid @RequestBody ProductosRequestDTO productosRequestDTO) {
        Productos producto = productoServices.createProducto(productosRequestDTO);
        ApiResponse<Productos> response = new ApiResponse<>(
                true,
                "Producto creado con éxito",
                HttpStatus.CREATED.value(),
                producto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllProductos() {
        List<Productos> productos = productoServices.findAll();
        ApiResponse<?> response = new ApiResponse<>(true, "Lista de productos obtenida", HttpStatus.OK.value(), productos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getProductoById(@PathVariable("id") Long id) {
        Productos producto = productoServices.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        ApiResponse<?> response = new ApiResponse<>(true, "Producto encontrado", HttpStatus.OK.value(), producto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Productos>> updateProducto(@PathVariable("id") Long id,
            @Valid @RequestBody ProductosRequestDTO productosRequestDTO) {
        productosRequestDTO.setId(id);
        Productos producto = productoServices.updateProducto(productosRequestDTO);
        ApiResponse<Productos> response = new ApiResponse<>(true, "Producto actualizado con éxito", HttpStatus.OK.value(),
                producto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProducto(@PathVariable("id") Long id) {
        productoServices.deleteProducto(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Producto eliminado con éxito",
                HttpStatus.NO_CONTENT.value(), "Producto con ID " + id + " ha sido eliminado.");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

