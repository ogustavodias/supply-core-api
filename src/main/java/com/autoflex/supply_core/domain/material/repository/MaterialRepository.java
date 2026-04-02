package com.autoflex.supply_core.domain.material.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.autoflex.supply_core.domain.material.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
   boolean existsByName(String name);

   Page<Material> findAll(Pageable pageable);

}
