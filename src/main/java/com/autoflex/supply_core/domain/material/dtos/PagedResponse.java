package com.autoflex.supply_core.domain.material.dtos;

import java.util.List;

import org.springframework.data.domain.Page;

public record PagedResponse<T>(List<T> content, long page, int limit, long totalElements) {

   public static <T> PagedResponse<T> fromPage(Page<T> page) {
      return new PagedResponse<>(
            page.getContent(),
            page.getPageable().getPageNumber(),
            page.getPageable().getPageSize(),
            page.getTotalElements());
   }

}
