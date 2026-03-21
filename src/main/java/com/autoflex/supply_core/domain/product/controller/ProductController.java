package com.autoflex.supply_core.domain.product.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.autoflex.supply_core.domain.material.model.Material;
import com.autoflex.supply_core.domain.material.service.MaterialService;
import com.autoflex.supply_core.domain.product.dtos.ProductCreate;
import com.autoflex.supply_core.domain.product.dtos.ProductResponse;
import com.autoflex.supply_core.domain.product.dtos.ProductUpdate;
import com.autoflex.supply_core.domain.product.model.Product;
import com.autoflex.supply_core.domain.product.service.ProductService;
import com.autoflex.supply_core.domain.product_material.dtos.ProductMaterialCreate;
import com.autoflex.supply_core.domain.product_material.dtos.ProductMaterialResponse;
import com.autoflex.supply_core.domain.product_material.service.ProductMaterialService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService productService;
   private final MaterialService materialService;
   private final ProductMaterialService productMaterialService;

   @PostMapping
   public ResponseEntity<Void> registerProduct(@RequestBody @Valid ProductCreate product) {
      Product savedProduct = productService.createProduct(product.toEntity());

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

      return ResponseEntity.ok().build();
   }

   @GetMapping("/{id}/materials")
   public ResponseEntity<List<ProductMaterialResponse>> getProductMaterials(@PathVariable Long id) {
      Product product = productService.getProduct(id);

      List<ProductMaterialResponse> productMaterials = productMaterialService.getAllProductMaterials(product)
            .stream()
            .map(ProductMaterialResponse::fromEntity)
            .toList();

      return ResponseEntity.ok(productMaterials);
   }

   @PostMapping("/{id}/materials/{material_id}")
   @ResponseStatus(code = org.springframework.http.HttpStatus.OK)
   public ResponseEntity<Void> addMaterial(
         @PathVariable(name = "id") Long productId,
         @PathVariable(name = "material_id") Long materialId,
         @RequestBody @Valid ProductMaterialCreate data) {
      Product product = productService.getProduct(productId);
      Material material = materialService.getMaterial(materialId);

      productMaterialService.addMaterial(data.toEntity(product, material));

      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}/materials/{material_id}")
   public ResponseEntity<Void> removeMaterial(
         @PathVariable(name = "id") Long productId,
         @PathVariable(name = "material_id") Long materialId) {
      Product product = productService.getProduct(productId);
      Material material = materialService.getMaterial(materialId);

      productMaterialService.removeMaterial(product, material);

      return ResponseEntity.ok().build();
   }

}
