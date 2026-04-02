package com.autoflex.supply_core.domain.material.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MaterialUpdate {

   @NotNull(message = "stock is required.")
   @Min(value = 1, message = "The stock must be greater than or equal to one.")
   final public Integer stock;

}
