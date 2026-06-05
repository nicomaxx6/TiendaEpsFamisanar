package com.famisanar.tienda.repository;

import com.famisanar.tienda.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByOrderByNombreAsc();

    Optional<Product> findTop1ByOrderByCantidadVendidaDesc();

    Optional<Product> findTop1ByOrderByCantidadVendidaAsc();
}
