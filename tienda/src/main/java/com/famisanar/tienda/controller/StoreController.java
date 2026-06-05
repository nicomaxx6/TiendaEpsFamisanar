package com.famisanar.tienda.controller;


import com.famisanar.tienda.dto.*;
import com.famisanar.tienda.service.IProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StoreController {

    @NonNull
    private IProductService iProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> fetchProducts(){
        List<ProductDto> productDtos = iProductService.fetchProducts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productDtos);
    }

    @PostMapping("/products/sell")
    public ResponseEntity<ResponseDto> sellProduct(@RequestBody SellProductDto requestDto) {
            double totalVentaConIva = iProductService.sellProduct(
                    requestDto.getIdProducto(),
                    requestDto.getCantidad()
            );
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("200","El producto se ha vendido con exito !!"));

    }

    @PutMapping("/products/restock")
    public ResponseEntity<?> restockProduct(@RequestBody OrderProductDto requestDto) {
        try {
            iProductService.restockProduct(requestDto.getIdProducto(), requestDto.getCantidad());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Pedido al proveedor procesado con éxito. El inventario ha sido actualizado !!"));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("400", e.getMessage()));
        }
    }

    @GetMapping("/products/statistics")
    public ResponseEntity<StoreStatisticsDto> getStoreStatistics() {
        StoreStatisticsDto statistics = iProductService.getStoreStatistics();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(statistics);
    }

}
