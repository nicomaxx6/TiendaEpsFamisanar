package com.famisanar.tienda.entity;
import jakarta.persistence.*;
import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto", schema = "db_tienda_management")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Integer idProducto;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "idTipo")
    private Integer idTipo;
}
