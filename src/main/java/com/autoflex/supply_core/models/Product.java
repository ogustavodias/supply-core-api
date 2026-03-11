package com.autoflex.supply_core.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_products")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;

   @Column(nullable = false, unique = true)
   private String name;

   @Column(nullable = false)
   private BigDecimal price;

   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
   @Builder.Default
   private List<ProductMaterial> materials = new ArrayList<>();

}
