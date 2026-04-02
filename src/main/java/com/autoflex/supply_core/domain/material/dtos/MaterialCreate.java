package com.autoflex.supply_core.domain.material.dtos;

import com.autoflex.supply_core.domain.material.model.Material;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MaterialCreate {

   @NotBlank(message = "name is required.")
   final public String name;

   @NotNull(message = "stock is required.")
   @Min(value = 1, message = "The stock must be greater than or equal to one.")
   final public Integer stock;

   public Material toEntity() {
      return Material.builder()
            .name(name)
            .stock(stock)
            .build();
   }
}
