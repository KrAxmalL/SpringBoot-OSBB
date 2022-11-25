package com.example.osbb.service;

import com.example.osbb.domain.model.User;
import com.example.osbb.domain.security.Principal;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PrincipalService principalService;

  @Override
  public boolean userExists(Integer userId) {
    return userRepository.existsById(userId);
  }

  @Override
  public User getUserFromSecurityContext() {
    final UsernamePasswordAuthenticationToken token =
        (UsernamePasswordAuthenticationToken)
            SecurityContextHolder.getContext().getAuthentication();
    final Principal principal =
        principalService.getPrincipalByEmail(token.getPrincipal().toString());
    return userRepository
        .findUserByCredentials(principal)
        .orElseThrow(
            () -> new EntityNotFoundException("User with given credentials doesn't exist"));
  }
}
