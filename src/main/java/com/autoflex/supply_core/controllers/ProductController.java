package com.autoflex.supply_core.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoflex.supply_core.dtos.ProductResponse;
import com.autoflex.supply_core.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService service;

   @GetMapping
   public ResponseEntity<List<ProductResponse>> getAllProducts() {
      return ResponseEntity.ok(service.getAllProducts());
   }
}
