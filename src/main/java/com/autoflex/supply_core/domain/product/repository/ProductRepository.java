package com.autoflex.supply_core.domain.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.autoflex.supply_core.domain.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
   Page<Product> findAllByOrderByPriceDesc(Pageable pageable);
}
