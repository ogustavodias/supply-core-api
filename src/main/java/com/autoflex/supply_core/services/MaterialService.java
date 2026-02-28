package com.autoflex.supply_core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autoflex.supply_core.errors.NotFoundException;
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

   public List<Material> getAllMaterials(List<Long> ids) {
      List<Material> found = repository.findAllById(ids);

      if (found.size() < ids.size())
         throw new NotFoundException("One or more materials were not found.");

      return found;
   }

   public Material getMaterial(Long id) {
      return repository.findById(id).orElseThrow(() -> new NotFoundException("Material not found."));
   }

   public Material saveMaterial(Material material) {
      return repository.save(material);
   }

   public void deleteMaterial(Material material) {
      repository.delete(material);
   }
}
