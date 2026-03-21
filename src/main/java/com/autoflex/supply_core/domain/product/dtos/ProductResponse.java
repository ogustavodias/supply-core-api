package com.autoflex.supply_core.domain.product.dtos;

import java.math.BigDecimal;

import com.autoflex.supply_core.domain.product.model.Product;

public record ProductResponse(Long id, String name, BigDecimal price, Boolean isProducible) {

   public static ProductResponse fromEntity(Product product) {
      return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.isProducible());
   }

}
