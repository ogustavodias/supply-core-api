package com.autoflex.supply_core.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.autoflex.supply_core.dtos.ProductMaterialCreate;
import com.autoflex.supply_core.dtos.ProductCreate;
import com.autoflex.supply_core.errors.NotFoundException;
import com.autoflex.supply_core.models.Material;
import com.autoflex.supply_core.models.Product;
import com.autoflex.supply_core.models.ProductMaterial;
import com.autoflex.supply_core.repositories.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

   private final ProductRepository repository;
   private final MaterialService materialService;

   public List<Product> getAllProducts() {
      return repository.findAllByOrderByPriceDesc();
   }

   public Product getProduct(Long id) {
      return repository.findById(id).orElseThrow(
            () -> new NotFoundException("Product not found."));
   }

   @Transactional
   public Product createProduct(ProductCreate product) {
      Map<Long, Integer> materialsAmount = product.getMaterials().stream().collect(Collectors.toMap(
            ProductMaterialCreate::getId, material -> material.getRequiredAmount()));

      List<Material> materials = materialService
            .getAllMaterials(product.getMaterials().stream().map(ProductMaterialCreate::getId).toList());

      Product productToSave = Product.builder()
            .name(product.getName())
            .price(product.getPrice())
            .build();

      List<ProductMaterial> productMaterials = materials.stream()
            .map(m -> ProductMaterial.builder()
                  .product(productToSave)
                  .material(m)
                  .requiredAmount(materialsAmount.get(m.getId()))
                  .build())
            .toList();

      productToSave.setMaterials(productMaterials);

      return repository.save(productToSave);
   }

   public void deleteProduct(Product product) {
      repository.delete(product);
   }

}
