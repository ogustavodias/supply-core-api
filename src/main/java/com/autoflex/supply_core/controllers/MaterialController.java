package com.autoflex.supply_core.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.autoflex.supply_core.dtos.MaterialCreate;
import com.autoflex.supply_core.dtos.MaterialEdit;
import com.autoflex.supply_core.models.Material;
import com.autoflex.supply_core.services.MaterialService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("materials")
@RequiredArgsConstructor
public class MaterialController {

   private final MaterialService service;

   @GetMapping
   public ResponseEntity<List<Material>> getAllMaterials() {
      return ResponseEntity.ok(service.getAllMaterials());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Material> getMaterial(@PathVariable Long id) {
      Material material = service.getMaterial(id);
      return ResponseEntity.ok(material);
   }

   @PostMapping
   public ResponseEntity<Void> registerMaterial(@RequestBody @Valid MaterialCreate request) {
      Material material = request.toEntity();
      Material savedMaterial = service.saveMaterial(material);

      URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedMaterial.getId())
            .toUri();

      return ResponseEntity.created(uri).build();
   }

   @PutMapping
   public ResponseEntity<Material> editMaterial(@RequestBody @Valid MaterialEdit request) {
      Material material = service.getMaterial(request.id());

      material.setName(request.name());
      material.setStock(request.stock());

      return ResponseEntity.ok(service.saveMaterial(material));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
      Material material = service.getMaterial(id);

      service.deleteMaterial(material);

      return ResponseEntity.ok(null);
   }

}
