package com.famisanar.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreStatisticsDto {
    private String productoMasVendido;
    private String productoMenosVendido;
    private double dineroTotalObtenido;
    private double promedioVentaPorUnidad;
}