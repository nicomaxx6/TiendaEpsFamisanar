package com.famisanar.tienda.service.impl;

import com.famisanar.tienda.dto.ProductDto;
import com.famisanar.tienda.dto.StoreStatisticsDto;
import com.famisanar.tienda.entity.Product;
import com.famisanar.tienda.exception.ResourceNotFound;
import com.famisanar.tienda.mapper.ProductMapper;
import com.famisanar.tienda.repository.ProductRepository;
import com.famisanar.tienda.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> fetchProducts() {
        List<Product>  products = productRepository.findAllByOrderByNombreAsc();
        List<ProductDto> dtoList = new ArrayList<>();

        for (Product product : products) {
            ProductDto dto = productMapper.toDto(product);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    @Transactional
    public double sellProduct(Integer idProducto, Integer cantidadAVender) {

        Product product = productRepository.findById(Long.valueOf(idProducto))
                .orElseThrow(() -> new ResourceNotFound(idProducto ));

        if (product.getCantidadActual() < cantidadAVender) {
            throw new RuntimeException("Stock insuficiente en bodega para el producto: " + product.getNombre()
                    + ". Unidades disponibles: " + product.getCantidadActual());
        }

        product.setCantidadActual(product.getCantidadActual() - cantidadAVender);

        int ventasActuales = (product.getCantidadVendida() != null) ? product.getCantidadVendida() : 0;
        product.setCantidadVendida(ventasActuales + cantidadAVender);

        productRepository.save(product);

        double precioBaseTotal = product.getPrecioBase() * cantidadAVender;
        double porcentajeIva = obtenerPorcentajeIva(product.getIdTipo());

        return precioBaseTotal * (1 + porcentajeIva);
    }

    @Override
    @Transactional
    public void restockProduct(Integer idProducto, Integer cantidadAComprar) {
        Product product = productRepository.findById(Long.valueOf(idProducto))
                .orElseThrow(() -> new ResourceNotFound(idProducto ));

        if (product.getCantidadActual() >= product.getCantidadMinima()) {
            throw new RuntimeException("No es necesario hacer un pedido al proveedor. El stock actual ("
                    + product.getCantidadActual() + ") no ha bajado del mínimo permitido ("
                    + product.getCantidadMinima() + ").");
        }

        int nuevoStock = product.getCantidadActual() + cantidadAComprar;
        product.setCantidadActual(nuevoStock);

        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public StoreStatisticsDto getStoreStatistics() {
        Product masVendido = productRepository.findTop1ByOrderByCantidadVendidaDesc().orElse(null);

        Product menosVendido = productRepository.findTop1ByOrderByCantidadVendidaAsc().orElse(null);

        List<Product> todosLosProductos = productRepository.findAll();
        double dineroTotalVentas = 0;
        int unidadesTotalesVendidas = 0;

        for (Product p : todosLosProductos) {
            int unidadesVendidas = (p.getCantidadVendida() != null) ? p.getCantidadVendida() : 0;

            if (unidadesVendidas > 0) {
                double precioBaseTotal = p.getPrecioBase() * unidadesVendidas;
                double iva = obtenerPorcentajeIva(p.getIdTipo());

                dineroTotalVentas += (precioBaseTotal * (1 + iva));
                unidadesTotalesVendidas += unidadesVendidas;
            }
        }

        double promedioVentas = (unidadesTotalesVendidas > 0) ? (dineroTotalVentas / unidadesTotalesVendidas) : 0.0;

        return StoreStatisticsDto.builder()
                .productoMasVendido(masVendido != null ? masVendido.getNombre() : "Sin ventas")
                .productoMenosVendido(menosVendido != null ? menosVendido.getNombre() : "Sin ventas")
                .dineroTotalObtenido(dineroTotalVentas)
                .promedioVentaPorUnidad(promedioVentas)
                .build();
    }

    private double obtenerPorcentajeIva(Integer idTipo) {
        if (idTipo == null) return 0.0;
        return switch (idTipo) {
            case 1 -> 0.16;
            case 2 -> 0.12;
            case 3 -> 0.04;
            default -> 0.0;
        };
    }

}
