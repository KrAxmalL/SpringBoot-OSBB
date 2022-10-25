package com.example.osbb.web;

import com.example.osbb.domain.dto.ErrorResponse;
import com.example.osbb.domain.dto.principal.RegisterPrincipalDTO;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.exception.EntityValidationException;
import com.example.osbb.service.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class PrincipalController {

  private final PrincipalService principalService;

  @PostMapping("/registration")
  public ResponseEntity<?> registerPrincipal(
      @RequestBody RegisterPrincipalDTO registerPrincipalDTO) {
    principalService.registerPrincipal(registerPrincipalDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
    final int status = HttpStatus.NOT_FOUND.value();
    return ErrorResponse.builder().status(status).message(ex.getMessage()).build();
  }

  @ExceptionHandler(EntityValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleEntityValidationException(EntityValidationException ex) {
    final int status = HttpStatus.BAD_REQUEST.value();
    return ErrorResponse.builder().status(status).message(ex.getMessage()).build();
  }
}
