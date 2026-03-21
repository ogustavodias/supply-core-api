package com.autoflex.supply_core.domain.product_material.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autoflex.supply_core.domain.material.model.Material;
import com.autoflex.supply_core.domain.product.model.Product;
import com.autoflex.supply_core.domain.product_material.model.ProductMaterial;
import com.autoflex.supply_core.domain.product_material.repository.ProductMaterialRepository;
import com.autoflex.supply_core.errors.NotFoundException;
import com.autoflex.supply_core.errors.NotPermittedException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductMaterialService {

      private final ProductMaterialRepository pmRepository;

      public List<ProductMaterial> getAllProductMaterials(Product product) {
            return pmRepository.findByProduct(product);
      }

      public ProductMaterial getProductMaterial(Product product, Material material) {
            return pmRepository.findByProductAndMaterial(product, material)
                        .orElseThrow(() -> new NotFoundException("Product material not found."));
      }

      @Transactional
      public void addMaterial(ProductMaterial productMaterial) {
            boolean alreadyAssociated = pmRepository.existsByProductAndMaterial(productMaterial.getProduct(),
                        productMaterial.getMaterial());

            if (alreadyAssociated)
                  throw new NotPermittedException("This material is already associated with the product.");

            pmRepository.save(productMaterial);
      }

      @Transactional
      public void removeMaterial(Product product, Material material) {
            pmRepository.delete(getProductMaterial(product, material));
      }

}
