package com.famisanar.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long idProducto;

    private String nombre;

    private Integer idTipo;

    private Integer cantidadActual;

    private Integer cantidadMinima;

    private Integer precioBase;

    private Integer cantidadVendida;
}
