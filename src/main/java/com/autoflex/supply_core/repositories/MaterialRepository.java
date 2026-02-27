package com.autoflex.supply_core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoflex.supply_core.models.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
