package com.example.osbb.web;

import com.example.osbb.domain.dto.ErrorResponse;
import com.example.osbb.domain.dto.principal.UpdateRolesDTO;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.exception.EntityValidationException;
import com.example.osbb.service.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/principals")
@RequiredArgsConstructor
public class PrincipalController {

  private final PrincipalService principalService;

  @PutMapping("/{principalId}")
  public ResponseEntity<?> updateRolesForPrincipal(
      @PathVariable Integer principalId, @RequestBody UpdateRolesDTO updateRolesDTO) {
    principalService.updateRolesForPrincipal(principalId, updateRolesDTO);
    return ResponseEntity.ok().build();
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
