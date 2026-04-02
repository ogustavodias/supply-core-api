package com.autoflex.supply_core.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalHandler {

   @ExceptionHandler(Exception.class)
   public ProblemDetail handleGlobal(Exception e) {
      String errorTrace = UUID.randomUUID().toString();
      log.error("Internal Server Error {}: {}", errorTrace, e.getMessage());

      ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      problem.setProperty("errorTrace", errorTrace);

      return problem;
   }

   @ExceptionHandler(NotPermittedException.class)
   public ProblemDetail handleNotPermitted(NotPermittedException e) {
      String errorTrace = UUID.randomUUID().toString();
      log.error("Not Permitted {}: {}", errorTrace, e.getMessage());

      ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_CONTENT);
      problem.setProperty("errorTrace", errorTrace);
      problem.setTitle(e.getMessage());

      return problem;
   }

   @ExceptionHandler(NotFoundException.class)
   public ProblemDetail handleNotFound(NotFoundException e) {
      String errorTrace = UUID.randomUUID().toString();
      log.error("Not Found {}: {}", errorTrace, e.getMessage());

      ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
      problem.setProperty("errorTrace", errorTrace);
      problem.setTitle(e.getMessage());

      return problem;
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
      String errorTrace = UUID.randomUUID().toString();
      log.error("Bad Request {}: {}", errorTrace,
            e.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));

      ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
      problem.setProperty("errorTrace", errorTrace);
      problem.setProperty("fields", e.getFieldErrors()
            .stream().collect(
                  Collectors.toMap(
                        FieldError::getField,
                        error -> List.of(error.getDefaultMessage()),
                        (currentMsg, newMsg) -> {
                           List<String> combined = new ArrayList<>(currentMsg);
                           combined.addAll(newMsg);
                           return combined;
                        })));

      return problem;
   }

}
