package com.example.osbb.security;

import com.example.osbb.service.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceAdapter implements UserDetailsService {

  private final PrincipalService principalService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return principalService.getPrincipalByEmail(username);
  }
}
