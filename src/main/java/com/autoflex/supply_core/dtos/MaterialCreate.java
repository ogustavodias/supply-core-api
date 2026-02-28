package com.autoflex.supply_core.dtos;

import com.autoflex.supply_core.models.Material;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MaterialCreate(
      @NotBlank(message = "name is required.") String name,
      @NotNull(message = "stock is required.") @Min(value = 1, message = "The stock must be greater than or equal to one.") Integer stock) {

   public Material toEntity() {
      return new Material(null, this.name, this.stock);
   }
}
