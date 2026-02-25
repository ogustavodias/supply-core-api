package com.autoflex.supply_core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_product_materials", uniqueConstraints = {
            @UniqueConstraint(columnNames = { "product_id", "raw_material_id" }) })
@Getter
@Setter
public class ProductMaterial {

      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE)
      private Long id;

      @ManyToOne
      @JoinColumn(name = "product_id", nullable = false)
      private Product product;

      @ManyToOne
      @JoinColumn(name = "raw_material_id", nullable = false)
      private RawMaterial rawMaterial;

      @Column(nullable = false)
      private Integer requiredAmount;

}
