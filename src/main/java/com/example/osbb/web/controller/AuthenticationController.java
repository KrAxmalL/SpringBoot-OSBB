package com.example.osbb.web.controller;

import com.example.osbb.domain.dto.jwt.JwtToken;
import com.example.osbb.domain.dto.principal.LoginPrincipalDTO;
import com.example.osbb.domain.dto.principal.RegisterPrincipalDTO;
import com.example.osbb.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "User registration")
@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @Operation(summary = "Login user by returning jwt token. Must provide valid email and password")
  @PostMapping("/login")
  public ResponseEntity<?> loginPrincipal(@RequestBody LoginPrincipalDTO loginPrincipalDTO) {
    try {
      JwtToken token = authenticationService.loginPrincipal(loginPrincipalDTO);
      return ResponseEntity.ok(token);
    } catch (AuthenticationException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @Operation(summary = "Register new user. Must provide valid unique email and password")
  @PostMapping("/registration")
  public ResponseEntity<?> registerPrincipal(
      @RequestBody @Valid RegisterPrincipalDTO registerPrincipalDTO) {
    authenticationService.registerPrincipal(registerPrincipalDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
