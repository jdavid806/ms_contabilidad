package com.medicalsoftcontable.medicalsoftcontable.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseService;
import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.DetalleFacturaRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.FacturaRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.enums.TiposProducto;
import com.medicalsoftcontable.medicalsoftcontable.models.DetallesFactura;
import com.medicalsoftcontable.medicalsoftcontable.models.Factura;
import com.medicalsoftcontable.medicalsoftcontable.models.Inventario;
import com.medicalsoftcontable.medicalsoftcontable.models.Productos;
import com.medicalsoftcontable.medicalsoftcontable.models.Tercero;
import com.medicalsoftcontable.medicalsoftcontable.repository.FacturaRepository;
import com.medicalsoftcontable.medicalsoftcontable.repository.InventarioRepository;
import com.medicalsoftcontable.medicalsoftcontable.repository.ProductoRepository;
import com.medicalsoftcontable.medicalsoftcontable.repository.TerceroRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FacturaService extends BaseService<Factura> {

    private final FacturaRepository facturaRepository;
    private final TerceroRepository terceroRepository;
    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;

    public FacturaService(
            ProductoRepository productoRepository,
            FacturaRepository facturaRepository,
            TerceroRepository terceroRepository,
            InventarioRepository inventarioRepository) {
        super(facturaRepository);
        this.facturaRepository = facturaRepository;
        this.terceroRepository = terceroRepository;
        this.inventarioRepository = inventarioRepository;
        this.productoRepository = productoRepository;
    }

    public FacturaRequestDTO crearFactura(FacturaRequestDTO facturaRequestDTO) {
        Tercero tercero = terceroRepository.findById(facturaRequestDTO.getTerceroId())
                .orElseThrow(() -> new RuntimeException("El tercero no existe"));

        Factura factura = new Factura();
        factura.setNumeroFactura(facturaRequestDTO.getNumeroFactura());
        factura.setFecha(facturaRequestDTO.getFecha());
        factura.setTipoFactura(facturaRequestDTO.getTipoFactura());
        factura.setEstado(facturaRequestDTO.getEstado());
        factura.setTercero(tercero);

        // Inicializar totales como BigDecimal
        BigDecimal totalFactura = BigDecimal.ZERO;
        BigDecimal totalImpuestos = BigDecimal.ZERO;
        List<DetallesFactura> detalles = new ArrayList<>();

        for (DetalleFacturaRequestDTO detalleDTO : facturaRequestDTO.getDetalles()) {
            Inventario inventario = inventarioRepository.findById(detalleDTO.getInventarioId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado en inventario"));

            Productos producto = inventario.getProducto();

            DetallesFactura detallesFactura = new DetallesFactura();
            detallesFactura.setInventario(inventario);
            detallesFactura.setCantidad(detalleDTO.getCantidad());
            detallesFactura.setPrecioUnitario(detalleDTO.getPrecioUnitario());

            BigDecimal cantidad = BigDecimal.valueOf(detalleDTO.getCantidad());
            BigDecimal subtotal = detalleDTO.getPrecioUnitario().multiply(cantidad);
            detallesFactura.setSubtotal(subtotal);

            BigDecimal impuestoTotal = calcularImpuestos(subtotal, producto);
            detallesFactura.setImpuesto(impuestoTotal);

            totalFactura = totalFactura.add(subtotal).add(impuestoTotal);
            totalImpuestos = totalImpuestos.add(impuestoTotal);

      
            if (!producto.getTipoProducto().equals(TiposProducto.SERVICIOS)) {
                if (inventario.getCantidadDisponible() < detalleDTO.getCantidad()) {
                    throw new RuntimeException("Stock insuficiente para el producto: " + inventario.getId());
                }
                inventario.setCantidadDisponible(inventario.getCantidadDisponible() - detalleDTO.getCantidad());
            }

            detallesFactura.setFactura(factura);
            detalles.add(detallesFactura);
        }

        factura.setSubtotal(totalFactura.subtract(totalImpuestos));
        factura.setDetalles(detalles);
        factura.setTotal(totalFactura);
        factura.setImpuestos(totalImpuestos);

        Factura facturaGuardada = facturaRepository.save(factura);
        inventarioRepository
                .saveAll(detalles.stream().map(DetallesFactura::getInventario).collect(Collectors.toList()));

        return convertirFacturaARequestDTO(facturaGuardada);

    }

    // public FacturaRequestDTO crearFacturaCompra(FacturaRequestDTO facturaRequestDTO) {
    //     Tercero tercero = terceroRepository.findById(facturaRequestDTO.getTerceroId())
    //             .orElseThrow(() -> new RuntimeException("El tercero no existe"));

    //     Factura factura = new Factura();
    //     factura.setNumeroFactura(facturaRequestDTO.getNumeroFactura());
    //     factura.setFecha(facturaRequestDTO.getFecha());
    //     factura.setTipoFactura(facturaRequestDTO.getTipoFactura());
    //     factura.setEstado(facturaRequestDTO.getEstado());
    //     factura.setTercero(tercero);

    //     BigDecimal totalFactura = BigDecimal.ZERO;
    //     BigDecimal totalImpuestos = BigDecimal.ZERO;
    //     List<DetallesFactura> detalles = new ArrayList<>();

    //     for (DetalleFacturaRequestDTO detalleDTO : facturaRequestDTO.getDetalles()) {
    //         Productos producto = productoRepository.findById(detalleDTO.getProductoId())
    //                 .orElseGet(() -> {
                        
    //                     ProductosRequestDTO nuevoProductoDTO = new ProductosRequestDTO();
    //                     nuevoProductoDTO.setNombre(detalleDTO.getNombre());
    //                     nuevoProductoDTO.setTipoProducto(detalleDTO.getTipoProducto());
    //                     nuevoProductoDTO.setImpuestosIds(detalleDTO.getImpuestosIds());
    //                     return createProducto(nuevoProductoDTO);
    //                 });

    //         Inventario inventario = inventarioRepository.findById(producto.getId())
    //                 .orElseGet(() -> {
    //                     // Si no existe en inventario, crearlo con stock 0
    //                     Inventario nuevoInventario = new Inventario();
    //                     nuevoInventario.setProducto(producto);
    //                     nuevoInventario.setCantidadDisponible(0);
    //                     return inventarioRepository.save(nuevoInventario);
    //                 });

            
    //         inventario.setCantidadDisponible(inventario.getCantidadDisponible() + detalleDTO.getCantidad());
    //         inventarioRepository.save(inventario);
    //     }

    //     factura.setSubtotal(totalFactura.subtract(totalImpuestos));
    //     factura.setDetalles(detalles);
    //     factura.setTotal(totalFactura);
    //     factura.setImpuestos(totalImpuestos);

    //     Factura facturaGuardada = facturaRepository.save(factura);
    //     inventarioRepository
    //             .saveAll(detalles.stream().map(DetallesFactura::getInventario).collect(Collectors.toList()));

    //     return convertirFacturaARequestDTO(facturaGuardada);
    // }

    public FacturaRequestDTO convertirFacturaARequestDTO(Factura factura) {
        FacturaRequestDTO facturaDTO = new FacturaRequestDTO();
        facturaDTO.setNumeroFactura(factura.getNumeroFactura());
        facturaDTO.setFecha(new java.sql.Date(factura.getFecha().getTime())); // Conversión correcta
        facturaDTO.setTotal(factura.getTotal());
        facturaDTO.setTipoFactura(factura.getTipoFactura());
        facturaDTO.setEstado(factura.getEstado());
        facturaDTO.setTerceroId(factura.getTercero().getId());

        List<DetalleFacturaRequestDTO> detallesDTO = factura.getDetalles().stream().map(detalle -> {
            DetalleFacturaRequestDTO detalleDTO = new DetalleFacturaRequestDTO();
            detalleDTO.setInventarioId(detalle.getInventario().getId());
            detalleDTO.setCantidad(detalle.getCantidad());
            detalleDTO.setPrecioUnitario(detalle.getPrecioUnitario());
            detalleDTO.setSubtotal(detalle.getSubtotal());
            return detalleDTO;
        }).collect(Collectors.toList());

        facturaDTO.setDetalles(detallesDTO);
        return facturaDTO;
    }

    private BigDecimal calcularImpuestos(BigDecimal subtotal, Productos producto) {
        return producto.getImpuestos().stream()
                .map(impuesto -> subtotal
                        .multiply(BigDecimal.valueOf(impuesto.getPorcentaje()).divide(BigDecimal.valueOf(100))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
