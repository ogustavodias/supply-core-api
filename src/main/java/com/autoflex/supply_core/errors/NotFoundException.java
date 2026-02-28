package com.autoflex.supply_core.errors;

public class NotFoundException extends RuntimeException {
   public NotFoundException(String msg) {
      super(msg);
   }
}
