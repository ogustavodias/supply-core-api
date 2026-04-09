package com.autoflex.supply_core.domain.material.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.autoflex.supply_core.domain.material.dtos.MaterialCreate;
import com.autoflex.supply_core.domain.material.dtos.MaterialUpdate;
import com.autoflex.supply_core.domain.material.model.Material;
import com.autoflex.supply_core.domain.material.service.MaterialService;
import com.autoflex.supply_core.global.dtos.PagedResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("materials")
@RequiredArgsConstructor
public class MaterialController {

   private final MaterialService service;

   @GetMapping
   public PagedResponse<Material> getAllMaterials(
         @RequestParam(defaultValue = "0", name = "page") int pageNumber,
         @RequestParam(defaultValue = "10") int limit) {
      Pageable pageable = Pageable.ofSize(limit).withPage(pageNumber);
      Page<Material> page = service.getAllMaterials(pageable);
      return PagedResponse.fromPage(page);
   }

   @PostMapping
   public ResponseEntity<Material> registerMaterial(@RequestBody @Valid MaterialCreate data) {
      Material savedMaterial = service.registerMaterial(data);

      URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedMaterial.getId())
            .toUri();

      return ResponseEntity.created(uri).body(savedMaterial);
   }

   @PatchMapping("/{id}")
   public Material editMaterial(@PathVariable Long id, @RequestBody @Valid MaterialUpdate data) {
      return service.editMaterial(id, data);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
      service.deleteMaterial(id);
      return ResponseEntity.ok().build();
   }

}
