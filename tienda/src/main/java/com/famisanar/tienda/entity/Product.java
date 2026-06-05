package com.famisanar.tienda.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto", schema = "db_tienda_management")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Long idProducto;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "idTipo")
    private Integer idTipo;

    @Column(name = "cantidadActual")
    private Integer cantidadActual;

    @Column(name = "cantidadMinima")
    private Integer cantidadMinima;

    @Column(name = "precioBase")
    private Integer precioBase;

    @Column(name = "cantidadVendida")
    private Integer cantidadVendida;
}
