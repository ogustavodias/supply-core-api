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
import com.autoflex.supply_core.errors.NotPermittedException;

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
      Assertions.assertDoesNotThrow(() -> service.registerMaterial(new MaterialCreate("Material Register Test", 20)));
      Mockito.verify(repository).save(Mockito.any());
   }

   @Test
   @DisplayName("should not register material if it already exists")
   void shouldNotRegisterMaterial() {
      Mockito.when(repository.existsByName("Material Register Test")).thenReturn(true);
      Assertions.assertThrows(NotPermittedException.class,
            () -> service.registerMaterial(new MaterialCreate("Material Register Test", 20)));
      Mockito.verify(repository, Mockito.never()).save(Mockito.any());
   }

}
