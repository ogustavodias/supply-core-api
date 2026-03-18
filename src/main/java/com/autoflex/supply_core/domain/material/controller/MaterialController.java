package com.autoflex.supply_core.domain.material.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.autoflex.supply_core.domain.material.dtos.MaterialCreate;
import com.autoflex.supply_core.domain.material.dtos.MaterialUpdate;
import com.autoflex.supply_core.domain.material.model.Material;
import com.autoflex.supply_core.domain.material.service.MaterialService;

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
   public ResponseEntity<Void> registerMaterial(@RequestBody @Valid MaterialCreate data) {
      Material savedMaterial = service.registerMaterial(data);

      URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedMaterial.getId())
            .toUri();

      return ResponseEntity.created(uri).build();
   }

   @PatchMapping("/{id}")
   public ResponseEntity<Void> editMaterial(@PathVariable Long id, @RequestBody @Valid MaterialUpdate request) {
      service.editMaterial(id, request);
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
      service.deleteMaterial(id);
      return ResponseEntity.ok().build();
   }

}
