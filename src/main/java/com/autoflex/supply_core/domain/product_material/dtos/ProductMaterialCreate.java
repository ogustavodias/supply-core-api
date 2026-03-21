package com.autoflex.supply_core.domain.product_material.dtos;

import com.autoflex.supply_core.domain.material.model.Material;
import com.autoflex.supply_core.domain.product.model.Product;
import com.autoflex.supply_core.domain.product_material.model.ProductMaterial;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductMaterialCreate {
   @NotNull(message = "amount is required.")
   @Min(value = 1, message = "The amount must be greater than or equal to one.")
   private Integer requiredAmount;

   public ProductMaterial toEntity(Product product, Material material) {
      return ProductMaterial.builder()
            .product(product)
            .material(material)
            .requiredAmount(requiredAmount)
            .build();
   }
}
