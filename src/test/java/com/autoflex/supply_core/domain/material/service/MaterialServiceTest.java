package com.autoflex.supply_core.domain.material.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.autoflex.supply_core.domain.material.dtos.MaterialCreate;
import com.autoflex.supply_core.domain.material.repository.MaterialRepository;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class MaterialServiceTest {

   @InjectMocks
   private MaterialService service;

   @Mock
   private MaterialRepository repository;

   @Test
   @DisplayName("should register material with success")
   void shouldRegisterMaterial() {
      Mockito.when(repository.existsByName("Material Register Test")).thenReturn(false);
      Assertions.assertDoesNotThrow(() -> service.registerMaterial(new MaterialCreate("Material Register Test")));
   }

}
