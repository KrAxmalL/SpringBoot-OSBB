package com.example.osbb.config;

import com.example.osbb.domain.security.Principal;
import com.example.osbb.domain.security.Role;
import java.util.List;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
public class SecurityTestConfig {

  @Bean
  @Primary
  public UserDetailsService userDetailsService() {
    Principal testAdmin =
        Principal.builder()
            .id(1)
            .email("admin@email.com")
            .password("123")
            .roles(List.of(Role.builder().id(1).name("admin").build()))
            .build();
    Principal testUser =
        Principal.builder()
            .id(1)
            .email("user@email.com")
            .password("123")
            .roles(List.of(Role.builder().id(2).name("user").build()))
            .build();
    return new InMemoryUserDetailsManager(List.of(testAdmin, testUser));
  }
}
