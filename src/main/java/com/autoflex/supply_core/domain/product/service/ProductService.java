package com.autoflex.supply_core.domain.product.service;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.autoflex.supply_core.domain.product.dtos.ProductUpdate;
import com.autoflex.supply_core.domain.product.model.Product;
import com.autoflex.supply_core.domain.product.repository.ProductRepository;

import com.autoflex.supply_core.errors.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

      private final ProductRepository repository;

      public Page<Product> getAllProducts(Pageable pageable) {
            return repository.findAllByOrderByPriceDesc(pageable);
      }

      public Product getProduct(Long id) {
            return repository.findById(id).orElseThrow(() -> new NotFoundException("Product not found."));
      }

      @Transactional
      public Product createProduct(Product product) {
            return repository.save(product);
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
