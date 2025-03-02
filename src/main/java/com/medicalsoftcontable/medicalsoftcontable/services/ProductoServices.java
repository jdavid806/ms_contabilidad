package com.medicalsoftcontable.medicalsoftcontable.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medicalsoftcontable.medicalsoftcontable.base.BaseService;
import com.medicalsoftcontable.medicalsoftcontable.dto.requestDTO.ProductosRequestDTO;
import com.medicalsoftcontable.medicalsoftcontable.models.Impuestos;
import com.medicalsoftcontable.medicalsoftcontable.models.Productos;
import com.medicalsoftcontable.medicalsoftcontable.repository.ImpuestoRepository;
import com.medicalsoftcontable.medicalsoftcontable.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoServices extends BaseService<Productos> {

    private final ImpuestoRepository impuestoRepository;

    public ProductoServices(ProductoRepository productoRepository, ImpuestoRepository impuestoRepository) {
        super(productoRepository);
        this.impuestoRepository = impuestoRepository;
    }

    public Productos createProducto(ProductosRequestDTO productosRequestDTO) {
        Productos producto = new Productos();
        producto.setTipoProducto(productosRequestDTO.getTipoProducto());
        producto.setCodigo(productosRequestDTO.getCodigo());
        producto.setNombre(productosRequestDTO.getNombre());
        producto.setIVAIncluido(productosRequestDTO.isIVAIncluido());
        producto.setPrecioVenta1(productosRequestDTO.getPrecioVenta1());
        producto.setPrecioVenta2(productosRequestDTO.getPrecioVenta2());
        producto.setPrecioCompra(productosRequestDTO.getPrecioCompra());
        producto.setCodigoBarra(productosRequestDTO.getCodigoBarra());
        producto.setDescripcion(productosRequestDTO.getDescripcion());
        producto.setDescripcionLarga(productosRequestDTO.getDescripcionLarga());
        producto.setUrlImagen(productosRequestDTO.getUrlImagen());
        producto.setInventariable(productosRequestDTO.isInventariable());
        producto.setMarca(productosRequestDTO.getMarca());


        if (productosRequestDTO.getImpuestosIds() != null && !productosRequestDTO.getImpuestosIds().isEmpty()) {
            List<Impuestos> impuestos = (List<Impuestos>) impuestoRepository.findAllById(productosRequestDTO.getImpuestosIds());            
            if (impuestos.size() != productosRequestDTO.getImpuestosIds().size()) {
                throw new RuntimeException("Uno o más impuestos no existen en la base de datos.");
            }
            producto.setImpuestos(impuestos);
        }
        return save(producto);

    }

    public Productos updateProducto(ProductosRequestDTO productosRequestDTO) {
        Productos producto = findById(productosRequestDTO.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                producto.setTipoProducto(productosRequestDTO.getTipoProducto());
                producto.setCodigo(productosRequestDTO.getCodigo());
                producto.setNombre(productosRequestDTO.getNombre());
                producto.setIVAIncluido(productosRequestDTO.isInventariable());
                producto.setPrecioVenta1(productosRequestDTO.getPrecioVenta1());
                producto.setPrecioVenta2(productosRequestDTO.getPrecioVenta2());
                producto.setPrecioCompra(productosRequestDTO.getPrecioCompra());
                producto.setCodigoBarra(productosRequestDTO.getCodigoBarra());
                producto.setDescripcion(productosRequestDTO.getDescripcion());
                producto.setDescripcionLarga(productosRequestDTO.getDescripcionLarga());
                producto.setUrlImagen(productosRequestDTO.getUrlImagen());
                producto.setInventariable(productosRequestDTO.isInventariable());
                producto.setMarca(productosRequestDTO.getMarca());
        
        
                if (productosRequestDTO.getImpuestosIds() != null && !productosRequestDTO.getImpuestosIds().isEmpty()) {
                    List<Impuestos> impuestos = (List<Impuestos>) impuestoRepository.findAllById(productosRequestDTO.getImpuestosIds());            
                    if (impuestos.size() != productosRequestDTO.getImpuestosIds().size()) {
                        throw new RuntimeException("Uno o más impuestos no existen en la base de datos.");
                    }
                    producto.setImpuestos(impuestos);
                }
                return save(producto);
    }



    public void deleteProducto(Long id) {
        Productos producto = findProductById(id); 
        delete(producto);  
    }

    public List<Productos> findAllProductoses() {
        return findAll(); 
    }

    public Productos findProductById(Long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
