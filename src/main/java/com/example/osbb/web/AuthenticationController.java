package com.example.osbb.web;

import com.example.osbb.domain.dto.ErrorResponse;
import com.example.osbb.domain.dto.principal.RegisterPrincipalDTO;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.exception.EntityValidationException;
import com.example.osbb.service.PrincipalService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.CloseableThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

  private final PrincipalService principalService;
  private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
  private final Marker API_CALL_MARKER = MarkerFactory.getMarker("API_CALL");

  private static final String REQUEST_ID_KEY = "requestId";

  @PostMapping("/registration")
  public ResponseEntity<?> registerPrincipal(
      @RequestBody RegisterPrincipalDTO registerPrincipalDTO) {
    try (final CloseableThreadContext.Instance ctx =
        CloseableThreadContext.put(REQUEST_ID_KEY, UUID.randomUUID().toString())) {
      logger.info(
          API_CALL_MARKER,
          "Registering user with email={} and password={}",
          registerPrincipalDTO.getEmail(),
          registerPrincipalDTO.getPassword());
      principalService.registerPrincipal(registerPrincipalDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
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
