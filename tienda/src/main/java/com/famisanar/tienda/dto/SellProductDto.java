package com.famisanar.tienda.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellProductDto {
    private Integer idProducto;
    private Integer cantidad;
}
