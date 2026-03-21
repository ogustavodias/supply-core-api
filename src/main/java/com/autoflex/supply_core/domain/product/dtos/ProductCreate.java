package com.autoflex.supply_core.domain.product.dtos;

import java.math.BigDecimal;

import com.autoflex.supply_core.domain.product.model.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreate {

   @NotBlank(message = "name is required.")
   private String name;

   @NotNull(message = "price is required.")
   @Min(value = 1, message = "The price must be greater than or equal to one.")
   private BigDecimal price;

   public Product toEntity() {
      return Product.builder()
            .name(name)
            .price(price)
            .build();
   }
}
