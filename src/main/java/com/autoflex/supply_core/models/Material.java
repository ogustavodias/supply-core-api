package com.autoflex.supply_core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_materials")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   @Setter(value = AccessLevel.NONE)
   private Long id;

   @Column(nullable = false, unique = true)
   private String name;

   @Column(nullable = false)
   private Integer stock;

}
