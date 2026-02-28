package com.autoflex.supply_core.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.autoflex.supply_core.dtos.ProductMaterialRequest;
import com.autoflex.supply_core.dtos.ProductRequest;
import com.autoflex.supply_core.errors.NotFoundException;
import com.autoflex.supply_core.models.Material;
import com.autoflex.supply_core.models.Product;
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
      Product product = repository.findById(id).orElseThrow(
            () -> new NotFoundException("Product not found."));

      return product;
   }

   @Transactional
   public Product saveProduct(ProductRequest data) {
      Product product = new Product();
      populateProduct(product, data);
      return repository.save(product);
   }

   private void populateProduct(Product product, ProductRequest data) {
      List<Long> materialsIds = data.getMaterials().stream().map(ProductMaterialRequest::getId).toList();
      List<Material> materials = materialService.getAllMaterials(materialsIds);
      Map<Long, Material> materialsMap = materials.stream().collect(
            Collectors.toMap(Material::getId, material -> material));

      product.setName(data.getName());
      product.setPrice(data.getPrice());
      data.getMaterials()
            .forEach(material -> product.addMaterial(materialsMap.get(material.getId()), material.getRequiredAmount()));
   }

   public void deleteProduct(Product product) {
      repository.delete(product);
   }

}
