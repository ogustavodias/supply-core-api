package com.autoflex.supply_core.domain.material.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoflex.supply_core.domain.material.dtos.MaterialCreate;
import com.autoflex.supply_core.domain.material.dtos.MaterialUpdate;
import com.autoflex.supply_core.domain.material.model.Material;
import com.autoflex.supply_core.domain.material.repository.MaterialRepository;
import com.autoflex.supply_core.errors.NotFoundException;
import com.autoflex.supply_core.errors.NotPermittedException;

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
         throw new NotFoundException("one or more materials were not found.");

      return found;
   }

   public Material getMaterial(Long id) {
      return repository.findById(id).orElseThrow(
            () -> new NotFoundException("material not found."));
   }

   public Material registerMaterial(MaterialCreate data) {
      Material toCreate = data.toEntity();

      if (repository.existsByName(data.getName()))
         throw new NotPermittedException("material with that name already registered");

      return repository.save(toCreate);
   }

   @Transactional
   public void editMaterial(Long id, MaterialUpdate data) {
      Material material = getMaterial(id);
      Integer updatedStock = data.getStock();
      material.setStock(updatedStock);
      repository.save(material);
   }

   @Transactional
   public void deleteMaterial(Long id) {
      Material material = getMaterial(id);
      repository.delete(material);
   }
}
