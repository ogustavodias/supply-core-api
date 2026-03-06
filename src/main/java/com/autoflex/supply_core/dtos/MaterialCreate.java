package com.autoflex.supply_core.dtos;

import com.autoflex.supply_core.models.Material;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialCreate extends MaterialUpdate {
   @NotBlank(message = "name is required.")
   private String name;

   public Material toEntity() {
      return Material.builder()
            .name(this.getName())
            .stock(this.getStock())
            .build();
   }
}
