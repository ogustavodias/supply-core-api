package com.autoflex.supply_core.domain.product_material.dtos;

import com.autoflex.supply_core.domain.product_material.model.ProductMaterial;

public record ProductMaterialResponse(Long id, String name, Integer stock,
      Integer requiredAmount) {

   public static ProductMaterialResponse fromEntity(ProductMaterial productMaterial) {
      return new ProductMaterialResponse(
            productMaterial.getMaterial().getId(),
            productMaterial.getMaterial().getName(),
            productMaterial.getMaterial().getStock(),
            productMaterial.getRequiredAmount());
   }

}
