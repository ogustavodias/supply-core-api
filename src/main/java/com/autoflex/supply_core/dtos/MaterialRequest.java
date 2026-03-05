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
public class MaterialRequest {

   @NotNull(message = "stock is required.")
   @Min(value = 1, message = "The stock must be greater than or equal to one.")
   private Integer stock;
}
