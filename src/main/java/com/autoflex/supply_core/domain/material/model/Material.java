package com.autoflex.supply_core.domain.material.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
public class Material {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   @Setter(value = AccessLevel.NONE)
   private Long id;

   @Column(nullable = false, unique = true)
   private String name;

   @Column(nullable = false)
   private Integer stock;

   @CreatedDate
   private Instant createdAt;

   @LastModifiedDate
   private Instant updatedAt;

}
