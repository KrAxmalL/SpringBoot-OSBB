package com.example.osbb.config;

import com.example.osbb.security.jwt.JWTManager;
import com.example.osbb.security.jwt.JwtProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class DisabledSecurityTestConfig {

  @Bean
  @Primary
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable().authorizeRequests().anyRequest().permitAll().and().build();
  }

  @Bean
  @Primary
  public JWTManager jwtManager() {
    return new JWTManager(new JwtProperties(""));
  }
}
