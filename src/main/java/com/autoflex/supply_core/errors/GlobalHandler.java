package com.autoflex.supply_core.errors;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalHandler {

   @ExceptionHandler(Exception.class)
   public ProblemDetail handleGlobal(Exception e) {
      String errorTrace = UUID.randomUUID().toString();
      log.error("Internal Server Error {}: ", errorTrace, e);

      ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      problem.setProperty("errorTrace", errorTrace);

      return problem;
   }

}
