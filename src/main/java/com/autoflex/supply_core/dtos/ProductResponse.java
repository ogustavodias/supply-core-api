package com.autoflex.supply_core.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.autoflex.supply_core.models.Product;

public record ProductResponse(Long id, String name, BigDecimal price, Boolean isProducible,
      List<ProductMaterialResponse> materials) {

   public static ProductResponse fromEntity(Product product) {
      return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getMaterials().stream().allMatch(m -> m.getMaterial().getStock() >= m.getRequiredAmount()),
            product.getMaterials().stream().map(ProductMaterialResponse::fromEntity).toList());
   }
}
