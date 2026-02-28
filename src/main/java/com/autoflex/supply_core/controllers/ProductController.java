package com.autoflex.supply_core.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.autoflex.supply_core.dtos.ProductRequest;
import com.autoflex.supply_core.dtos.ProductResponse;
import com.autoflex.supply_core.models.Product;
import com.autoflex.supply_core.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService service;

   @GetMapping
   public ResponseEntity<List<ProductResponse>> getAllProducts() {
      List<ProductResponse> products = service.getAllProducts().stream().map(ProductResponse::fromEntity).toList();
      return ResponseEntity.ok(products);
   }

   @GetMapping("/{id}")
   public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
      ProductResponse product = ProductResponse.fromEntity(service.getProduct(id));
      return ResponseEntity.ok(product);
   }

   @PostMapping
   public ResponseEntity<Void> registerProduct(@RequestBody @Valid ProductRequest request) {
      Product savedProduct = service.saveProduct(request);

      URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedProduct.getId())
            .toUri();

      return ResponseEntity.created(uri).build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
      Product product = service.getProduct(id);

      service.deleteProduct(product);

      return ResponseEntity.ok(null);
   }

}
