package com.autoflex.supply_core.dtos;

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
public class ProductMaterialRequest {
   @NotNull(message = "id of material is required.")
   private Long id;

   @NotNull(message = "amount is required.")
   @Min(value = 1, message = "The amount must be greater than or equal to one.")
   private Integer requiredAmount;
}
