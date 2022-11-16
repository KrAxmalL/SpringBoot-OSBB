package com.example.osbb.security.config;

import com.example.osbb.security.UserDetailsServiceAdapter;
import com.example.osbb.security.jwt.JwtAuthorizationFilter;
import com.example.osbb.service.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
  private final JwtAuthorizationFilter jwtAuthorizationFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/api/authentication/**", "/h2-console/**")
        .permitAll()
        .antMatchers("/api/principals/**", "/api/roles/**")
        .hasAuthority(AllowedRoles.ADMIN)
        .antMatchers("/api/advertisements/**", "/api/currencies/**")
        .hasAnyAuthority(AllowedRoles.USER, AllowedRoles.ADMIN)
        .anyRequest()
        .authenticated();

    http.headers().frameOptions().disable();

    http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  @Autowired
  public UserDetailsService userDetailsService(PrincipalService principalService) {
    return new UserDetailsServiceAdapter(principalService);
  }
}
