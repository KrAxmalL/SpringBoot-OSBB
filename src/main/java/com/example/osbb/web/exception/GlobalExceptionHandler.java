package com.example.osbb.web.exception;

import com.example.osbb.domain.dto.ErrorResponse;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.exception.EntityValidationException;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
    final String message =
        ex.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining("; "));
    final int status = HttpStatus.BAD_REQUEST.value();
    return ErrorResponse.builder().status(status).message(message).build();
  }

  @ExceptionHandler(EntityValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidationException(EntityValidationException ex) {
    final int status = HttpStatus.BAD_REQUEST.value();
    return ErrorResponse.builder().status(status).message(ex.getMessage()).build();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
    final int status = HttpStatus.NOT_FOUND.value();
    return ErrorResponse.builder().status(status).message(ex.getMessage()).build();
  }
}
