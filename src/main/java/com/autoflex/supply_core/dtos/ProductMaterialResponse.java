package com.autoflex.supply_core.dtos;

import com.autoflex.supply_core.models.ProductMaterial;

public record ProductMaterialResponse(Long id, Long materialId, String materialName, Integer stock,
      Integer requiredAmount) {

   public static ProductMaterialResponse fromEntity(ProductMaterial productMaterial) {
      return new ProductMaterialResponse(
            productMaterial.getId(),
            productMaterial.getMaterial().getId(),
            productMaterial.getMaterial().getName(),
            productMaterial.getMaterial().getStock(),
            productMaterial.getRequiredAmount());
   }

}
