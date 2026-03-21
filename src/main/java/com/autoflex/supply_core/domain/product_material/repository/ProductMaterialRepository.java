package com.autoflex.supply_core.domain.product_material.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoflex.supply_core.domain.material.model.Material;
import com.autoflex.supply_core.domain.product.model.Product;
import com.autoflex.supply_core.domain.product_material.model.ProductMaterial;

public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Long> {

   boolean existsByProductAndMaterial(Product product, Material material);

   Optional<ProductMaterial> findByProductAndMaterial(Product product, Material material);

   List<ProductMaterial> findByProduct(Product product);
}
