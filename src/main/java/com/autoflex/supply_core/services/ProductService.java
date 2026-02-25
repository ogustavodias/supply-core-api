package com.autoflex.supply_core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autoflex.supply_core.dtos.ProductResponse;
import com.autoflex.supply_core.dtos.ProductResponse.MaterialAmount;
import com.autoflex.supply_core.models.Product;
import com.autoflex.supply_core.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

   private final ProductRepository repository;

   public List<ProductResponse> getAllProducts() {
      return repository.findAllByOrderByPriceDesc().stream().map(p -> {
         List<MaterialAmount> materials = getMaterialsAmountsOfProduct(p);

         return new ProductResponse(
               p.getName(),
               p.getPrice(),
               materials,
               materials.stream().allMatch(m -> m.stock() >= m.requiredAmount()));
      }).toList();
   }

   private List<MaterialAmount> getMaterialsAmountsOfProduct(Product product) {
      return product.getMaterials()
            .stream()
            .map(m -> new MaterialAmount(
                  m.getRawMaterial().getName(),
                  m.getRawMaterial().getStock(),
                  m.getRequiredAmount()))
            .toList();
   }

}
