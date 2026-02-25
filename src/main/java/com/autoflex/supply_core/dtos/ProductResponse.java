package com.autoflex.supply_core.dtos;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(String name, BigDecimal price, List<MaterialAmount> materials, Boolean isProducible) {

   public record MaterialAmount(String name, Integer stock, Integer requiredAmount) {
   }
}
