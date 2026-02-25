package com.autoflex.supply_core.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_products")
@Getter
@Setter
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;

   @Column(nullable = false, unique = true)
   private String name;

   @Column(nullable = false)
   private BigDecimal price;

   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<ProductMaterial> materials;

}
