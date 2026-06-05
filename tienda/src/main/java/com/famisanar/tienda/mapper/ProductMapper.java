package com.famisanar.tienda.mapper;

import com.famisanar.tienda.dto.ProductDto;
import com.famisanar.tienda.entity.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    public ProductDto toDto(Product entity) {
        if (entity == null) return null;

        return ProductDto.builder()
                .idProducto(entity.getIdProducto())
                .nombre(entity.getNombre())
                .idTipo(entity.getIdTipo())
                .cantidadActual(entity.getCantidadActual())
                .cantidadMinima(entity.getCantidadMinima())
                .precioBase(entity.getPrecioBase())
                .cantidadVendida(entity.getCantidadVendida())
                .build();
    }

}
