package com.autoflex.supply_core.domain.product.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.autoflex.supply_core.domain.material.model.Material;
import com.autoflex.supply_core.domain.material.service.MaterialService;
import com.autoflex.supply_core.domain.product.dtos.ProductCreate;
import com.autoflex.supply_core.domain.product.dtos.ProductUpdate;
import com.autoflex.supply_core.domain.product.model.Product;
import com.autoflex.supply_core.domain.product.repository.ProductRepository;
import com.autoflex.supply_core.domain.product_material.dtos.ProductMaterialCreate;
import com.autoflex.supply_core.domain.product_material.model.ProductMaterial;
import com.autoflex.supply_core.errors.NotFoundException;

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

      @Transactional
      public void updateProduct(Long id, ProductUpdate product) {
            Product foundProduct = getProduct(id);

            if (!Objects.equals(product.name(), foundProduct.getName()))
                  foundProduct.setName(product.name());

            if (!Objects.equals(product.price(), foundProduct.getPrice()))
                  foundProduct.setPrice(product.price());

            repository.save(foundProduct);
      }

      public void deleteProduct(Product product) {
            repository.delete(product);
      }

}
