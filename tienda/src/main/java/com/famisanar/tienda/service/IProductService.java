package com.famisanar.tienda.service;

import com.famisanar.tienda.dto.ProductDto;
import com.famisanar.tienda.dto.StoreStatisticsDto;

import java.util.List;

public interface IProductService {

    List<ProductDto> fetchProducts();

    double sellProduct(Integer idProducto, Integer cantidadAVender);

    void restockProduct(Integer idProducto, Integer cantidadAComprar);

    StoreStatisticsDto getStoreStatistics();
}
