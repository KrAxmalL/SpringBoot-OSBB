package com.example.osbb.web.controller;

import com.example.osbb.domain.dto.principal.RegisterPrincipalDTO;
import com.example.osbb.service.PrincipalService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

  private final PrincipalService principalService;

  @PostMapping("/registration")
  public ResponseEntity<?> registerPrincipal(
      @RequestBody @Valid RegisterPrincipalDTO registerPrincipalDTO) {
    principalService.registerPrincipal(registerPrincipalDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
