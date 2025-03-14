package com.medicalsoftcontable.medicalsoftcontable.core.inventory.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseService;
import com.medicalsoftcontable.medicalsoftcontable.core.inventory.dto.requestDTO.InventarioRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.core.inventory.models.Inventario;
import com.medicalsoftcontable.medicalsoftcontable.core.inventory.models.Productos;
import com.medicalsoftcontable.medicalsoftcontable.core.inventory.repository.InventarioRepository;
import com.medicalsoftcontable.medicalsoftcontable.core.inventory.repository.ProductoRepository;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioService extends BaseService<Inventario> {

    private final ProductoRepository productoRepository;

    public InventarioService(InventarioRepository inventarioRepository, ProductoRepository productoRepository) {
        super(inventarioRepository);
        this.productoRepository = productoRepository;
    }

    public Inventario createInventario(InventarioRequestDTO inventarioRequestDTO) {
        Inventario inventario = new Inventario();
        inventario.setProducto(productoRepository.findById(inventarioRequestDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        inventario.setCantidad(inventarioRequestDTO.getCantidad());
        inventario.setCantidadDisponible(inventarioRequestDTO.getCantidad()); 
        inventario.setPrecioCompra(inventarioRequestDTO.getCostoUnitario());
        inventario.setLote(inventarioRequestDTO.getLote());
        inventario.setFechaVencimiento(inventarioRequestDTO.getFechaVencimiento());
        inventario.setUbicacion(inventarioRequestDTO.getUbicacion()); 
        return save(inventario);
    }
    
    public Inventario updateInventario(Long id, InventarioRequestDTO inventarioRequestDTO) {
        Inventario inventario = findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        Productos producto = productoRepository.findById(inventarioRequestDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        inventario.setProducto(producto);
        inventario.setCantidad(inventarioRequestDTO.getCantidad());
        inventario.setPrecioCompra(inventarioRequestDTO.getCostoUnitario());
        inventario.setUbicacion("Almacén principal"); // Ajustable
        inventario.setLote(inventarioRequestDTO.getLote());
        inventario.setFechaVencimiento(inventarioRequestDTO.getFechaVencimiento());

        return save(inventario);
    }

    public void deleteInventario(Long id) {
        Inventario inventario = findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        delete(inventario);
    }

    public List<Inventario> findAllInventarios() {
        return findAll();
    }

    public Inventario findInventarioById(Long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }
}
