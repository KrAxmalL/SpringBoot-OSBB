package com.example.osbb.web.controller;

import com.example.osbb.config.SecurityTestConfig;
import com.example.osbb.domain.dto.principal.LoginPrincipalDTO;
import com.example.osbb.domain.security.Principal;
import com.example.osbb.domain.security.Role;
import com.example.osbb.security.jwt.JWTManager;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "spring.main.allow-bean-definition-overriding=true")
@Import(SecurityTestConfig.class)
public class SecurityConfigurationTest {

  @Autowired private WebTestClient webTestClient;

  @Autowired private JWTManager jwtManager;

  @Test
  public void shouldReturnForbiddenIfNoJwtTokenProvided() {
    webTestClient
        .get()
        .uri("/api/advertisement")
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.FORBIDDEN);
  }

  @Test
  public void shouldAllowAccessToLoginWithoutAuthentication() {
    LoginPrincipalDTO loginPrincipalDTO = new LoginPrincipalDTO("user@email.com", "123");

    webTestClient
        .post()
        .uri("/api/authentication/login")
        .bodyValue(loginPrincipalDTO)
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.OK);
  }

  @Test
  public void shouldAllowAccessToAdvertisementForUserAndForAdmin() throws Exception {
    String defaultUserToken =
        jwtManager.getAccessToken(
            Principal.builder()
                .email("user@email.com")
                .roles(List.of(Role.builder().name("user").build()))
                .build());
    String adminToken =
        jwtManager.getAccessToken(
            Principal.builder()
                .email("admin@email.com")
                .roles(List.of(Role.builder().name("admin").build()))
                .build());

    webTestClient
        .get()
        .uri("/api/advertisements")
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + defaultUserToken)
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.OK);
    webTestClient
        .get()
        .uri("/api/advertisements")
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.OK);
  }

  @Test
  public void shouldAllowAccessToRolesOnlyForAdmin() {
    String defaultUserToken =
        jwtManager.getAccessToken(
            Principal.builder()
                .email("user@email.com")
                .roles(List.of(Role.builder().name("user").build()))
                .build());
    String adminToken =
        jwtManager.getAccessToken(
            Principal.builder()
                .email("admin@email.com")
                .roles(List.of(Role.builder().name("admin").build()))
                .build());

    webTestClient
        .get()
        .uri("/api/roles")
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + defaultUserToken)
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.FORBIDDEN);
    webTestClient
        .get()
        .uri("/api/roles")
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.OK);
  }
}
