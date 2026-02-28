package com.autoflex.supply_core.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.autoflex.supply_core.models.Product;
import com.autoflex.supply_core.models.ProductMaterial;

public record ProductResponse(
      Long id,
      String name,
      BigDecimal price,
      List<MaterialAmount> materials,
      Boolean isProducible) {

   public static ProductResponse fromEntity(Product product) {
      List<MaterialAmount> materials = product.getMaterials().stream().map(MaterialAmount::fromEntity).toList();
      return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            materials,
            materials.stream().allMatch(m -> m.stock() >= m.requiredAmount()));
   }

   public record MaterialAmount(Long id, String name, Integer stock, Integer requiredAmount) {
      public static MaterialAmount fromEntity(ProductMaterial productMaterial) {
         return new MaterialAmount(
               productMaterial.getMaterial().getId(),
               productMaterial.getMaterial().getName(),
               productMaterial.getMaterial().getStock(),
               productMaterial.getRequiredAmount());
      }
   }
}
