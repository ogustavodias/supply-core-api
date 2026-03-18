package com.autoflex.supply_core.domain.product_material.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.autoflex.supply_core.domain.material.model.Material;
import com.autoflex.supply_core.domain.material.repository.MaterialRepository;
import com.autoflex.supply_core.domain.product.model.Product;
import com.autoflex.supply_core.domain.product.repository.ProductRepository;
import com.autoflex.supply_core.domain.product_material.dtos.ProductMaterialCreate;
import com.autoflex.supply_core.domain.product_material.model.ProductMaterial;
import com.autoflex.supply_core.errors.NotFoundException;
import com.autoflex.supply_core.errors.NotPermittedException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductMaterialService {

      private final ProductRepository productRepository;
      private final MaterialRepository materialRepository;

      @Transactional
      public void addMaterial(Long productId, ProductMaterialCreate data) {
            Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new NotFoundException("Product not found."));
            Material material = materialRepository.findById(data.getId())
                        .orElseThrow(() -> new NotFoundException("Material not found."));

            product.getMaterials().add(
                        ProductMaterial.builder()
                                    .product(product)
                                    .material(material)
                                    .requiredAmount(data.getRequiredAmount())
                                    .build());

            productRepository.save(product);
      }

      @Transactional
      public void removeMaterial(Long productId, Long materialId) {
            Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new NotFoundException("Product not found."));

            if (product.getMaterials().size() == 1)
                  throw new NotPermittedException("A product must have at least one material.");

            product.getMaterials().removeIf(item -> Objects.equals(item.getMaterial().getId(), materialId));

            productRepository.save(product);
      }

}
