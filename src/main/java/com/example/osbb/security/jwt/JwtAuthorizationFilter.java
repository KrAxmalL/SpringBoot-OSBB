package com.example.osbb.security.jwt;

import com.example.osbb.domain.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private static final List<String> ignoredPaths =
      List.of("/api/authentication/registration", "/api/authentication/login");
  private static final String BEARER_STR = "Bearer ";
  private static final int BEARER_LENGTH = BEARER_STR.length();

  private final JWTManager jwtManager;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final String path = request.getServletPath();
    if (ignoredPaths.contains(path)) {
      filterChain.doFilter(request, response);
    } else {
      Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
          .filter(header -> header.startsWith(BEARER_STR))
          .map(header -> header.substring(BEARER_LENGTH))
          .ifPresentOrElse(
              token -> performAuthentication(token, request, response, filterChain),
              () -> continueFilterChain(request, response, filterChain));
    }
  }

  @SneakyThrows
  private void performAuthentication(
      String token,
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) {
    try {
      String username = jwtManager.getEmail(token);
      Collection<SimpleGrantedAuthority> authorities =
          jwtManager.getRoles(token).stream()
              .map(SimpleGrantedAuthority::new)
              .collect(Collectors.toList());

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(username, null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      ErrorResponse errorResponse =
          ErrorResponse.builder()
              .status(HttpStatus.UNAUTHORIZED.value())
              .message(ex.getMessage())
              .build();
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
  }

  @SneakyThrows
  private void continueFilterChain(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
    filterChain.doFilter(request, response);
  }
}
