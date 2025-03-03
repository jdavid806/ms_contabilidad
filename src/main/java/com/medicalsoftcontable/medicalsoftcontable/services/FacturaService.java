package com.medicalsoftcontable.medicalsoftcontable.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseService;
import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.DetalleFacturaRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.FacturaRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.enums.TipoDetalles;
import com.medicalsoftcontable.medicalsoftcontable.enums.TiposProducto;
import com.medicalsoftcontable.medicalsoftcontable.models.AsientoContable;
import com.medicalsoftcontable.medicalsoftcontable.models.CuentaContable;
import com.medicalsoftcontable.medicalsoftcontable.models.DetalleAsiento;
import com.medicalsoftcontable.medicalsoftcontable.models.DetallesFactura;
import com.medicalsoftcontable.medicalsoftcontable.models.Factura;
import com.medicalsoftcontable.medicalsoftcontable.models.Inventario;
import com.medicalsoftcontable.medicalsoftcontable.models.Productos;
import com.medicalsoftcontable.medicalsoftcontable.models.Tercero;
import com.medicalsoftcontable.medicalsoftcontable.repository.AsientoContableRepository;
import com.medicalsoftcontable.medicalsoftcontable.repository.CuentaContableRepository;
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
    private final CuentaContableRepository cuentaContableRepository;
    private final AsientoContableRepository asientoContableRepository;

    public FacturaService(
            ProductoRepository productoRepository,
            FacturaRepository facturaRepository,
            TerceroRepository terceroRepository,
            InventarioRepository inventarioRepository,
            CuentaContableRepository cuentaContableRepository,
            AsientoContableRepository asientoContableRepository) {
        super(facturaRepository);
        this.facturaRepository = facturaRepository;
        this.terceroRepository = terceroRepository;
        this.inventarioRepository = inventarioRepository;
        this.cuentaContableRepository = cuentaContableRepository;
        this.asientoContableRepository = asientoContableRepository;

    }

    @Transactional
    public FacturaRequestDTO crearFactura(FacturaRequestDTO facturaRequestDTO) {
        Tercero tercero = terceroRepository.findById(facturaRequestDTO.getTerceroId())
                .orElseThrow(() -> new RuntimeException("El tercero no existe"));

        Factura factura = new Factura();
        factura.setNumeroFactura(facturaRequestDTO.getNumeroFactura());
        factura.setFecha(facturaRequestDTO.getFecha());
        factura.setTipoFactura(facturaRequestDTO.getTipoFactura());
        factura.setEstado(facturaRequestDTO.getEstado());
        factura.setTercero(tercero);

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
        inventarioRepository.saveAll(
                detalles.stream().map(DetallesFactura::getInventario).collect(Collectors.toList()));

        AsientoContable asiento = generarAsientoContable(facturaGuardada);
        asientoContableRepository.save(asiento);

        return convertirFacturaARequestDTO(facturaGuardada);
    }

    private AsientoContable generarAsientoContable(Factura factura) {
        AsientoContable asiento = new AsientoContable();
        asiento.setNumeroAsiento("AS-" + factura.getNumeroFactura());
        asiento.setFechaAsiento(factura.getFecha()); 
        asiento.setDescripcion("Asiento contable de factura " + factura.getNumeroFactura());
        asiento.setEstado("PENDIENTE");
        asiento.setUsuarioId(1); 
    
        List<DetalleAsiento> detallesAsiento = new ArrayList<>();
        BigDecimal totalDebe = BigDecimal.ZERO;
        BigDecimal totalHaber = BigDecimal.ZERO;
    
   
        DetalleAsiento debito = new DetalleAsiento();
        debito.setMonto(factura.getTotal());
        debito.setTipo(TipoDetalles.DEBE);
        debito.setCuentaContable(obtenerCuentaContable("CUENTAS_POR_COBRAR"));
        debito.setAsientoContable(asiento);
        detallesAsiento.add(debito);
        totalDebe = totalDebe.add(factura.getTotal());
    
   
        DetalleAsiento creditoVentas = new DetalleAsiento();
        creditoVentas.setMonto(factura.getSubtotal());
        creditoVentas.setTipo(TipoDetalles.HABER);
        creditoVentas.setCuentaContable(obtenerCuentaContable("VENTAS"));
        creditoVentas.setAsientoContable(asiento); 
        detallesAsiento.add(creditoVentas);
        totalHaber = totalHaber.add(factura.getSubtotal());
    
        
        DetalleAsiento creditoImpuestos = new DetalleAsiento();
        creditoImpuestos.setMonto(factura.getImpuestos());
        creditoImpuestos.setTipo(TipoDetalles.HABER);
        creditoImpuestos.setCuentaContable(obtenerCuentaContable("IMPUESTOS_POR_PAGAR"));
        creditoImpuestos.setAsientoContable(asiento);  
        detallesAsiento.add(creditoImpuestos);
        totalHaber = totalHaber.add(factura.getImpuestos());
    
        asiento.setTotalDebe(totalDebe);
        asiento.setTotalHaber(totalHaber);
    
        asiento.setDetallesAsientos(detallesAsiento);  
    
        return asiento;
    }
    
    

    private CuentaContable obtenerCuentaContable(String nombre) {
        return cuentaContableRepository.findByNombreCuenta(nombre)
                .orElseThrow(() -> new RuntimeException("Cuenta contable no encontrada: " + nombre));
    }

    public FacturaRequestDTO convertirFacturaARequestDTO(Factura factura) {
        FacturaRequestDTO facturaDTO = new FacturaRequestDTO();
        facturaDTO.setNumeroFactura(factura.getNumeroFactura());
        facturaDTO.setFecha(new java.sql.Date(factura.getFecha().getTime()));
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
