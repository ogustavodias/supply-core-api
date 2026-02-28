package com.autoflex.supply_core.dtos;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

   @NotBlank(message = "name is required.")
   private String name;

   @NotNull(message = "price is required.")
   @Min(value = 1, message = "The price must be greater than or equal to one.")
   private BigDecimal price;

   @NotEmpty(message = "At least one Material is required to create the Product.")
   @Valid
   private List<ProductMaterialRequest> materials;

}
