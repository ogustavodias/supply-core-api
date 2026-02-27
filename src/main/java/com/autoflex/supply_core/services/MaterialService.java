package com.autoflex.supply_core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autoflex.supply_core.models.Material;
import com.autoflex.supply_core.repositories.MaterialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {

   private final MaterialRepository repository;

   public List<Material> getAllMaterials() {
      return repository.findAll();
   }

   public void saveMaterial(Material material) {
      repository.save(material);
   }

   public void deleteMaterial(Long id) {
      repository.deleteById(id);
   }
}
