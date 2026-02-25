package com.autoflex.supply_core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autoflex.supply_core.dtos.ProductResponse;
import com.autoflex.supply_core.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

   private final ProductRepository repository;

   public List<ProductResponse> getAllProducts() {
      return repository.findAllByOrderByPriceDesc().stream().map(ProductResponse::fromEntity).toList();
   }

}
