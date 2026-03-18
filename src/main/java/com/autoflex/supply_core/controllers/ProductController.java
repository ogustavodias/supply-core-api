package com.autoflex.supply_core.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.autoflex.supply_core.dtos.ProductCreate;
import com.autoflex.supply_core.dtos.ProductMaterialCreate;
import com.autoflex.supply_core.dtos.ProductResponse;
import com.autoflex.supply_core.dtos.ProductUpdate;
import com.autoflex.supply_core.models.Product;
import com.autoflex.supply_core.services.ProductMaterialService;
import com.autoflex.supply_core.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService productService;
   private final ProductMaterialService productMaterialService;

   @PostMapping
   public ResponseEntity<Void> registerProduct(@RequestBody @Valid ProductCreate product) {
      Product savedProduct = productService.createProduct(product);

      URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedProduct.getId())
            .toUri();

      return ResponseEntity.created(uri).build();
   }

   @GetMapping
   public ResponseEntity<List<ProductResponse>> getAllProducts() {
      List<ProductResponse> products = productService.getAllProducts().stream().map(ProductResponse::fromEntity)
            .toList();
      return ResponseEntity.ok(products);
   }

   @GetMapping("/{id}")
   public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
      ProductResponse product = ProductResponse.fromEntity(productService.getProduct(id));
      return ResponseEntity.ok(product);
   }

   @PatchMapping("/{id}")
   public ResponseEntity<Void> editProduct(@PathVariable Long id, @RequestBody ProductUpdate product) {
      productService.updateProduct(id, product);
      return ResponseEntity.noContent().build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
      Product product = productService.getProduct(id);

      productService.deleteProduct(product);

      return ResponseEntity.ok(null);
   }

   @PostMapping("/{id}/materials")
   public ResponseEntity<Void> addMaterial(
         @PathVariable(name = "id") Long productId,
         @RequestBody @Valid ProductMaterialCreate data) {
      productMaterialService.addMaterial(productId, data);
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}/materials/{material_id}")
   public ResponseEntity<Void> removeMaterial(
         @PathVariable(name = "id") Long productId,
         @PathVariable(name = "material_id") Long materialId) {
      productMaterialService.removeMaterial(productId, materialId);
      return ResponseEntity.ok().build();
   }

}
