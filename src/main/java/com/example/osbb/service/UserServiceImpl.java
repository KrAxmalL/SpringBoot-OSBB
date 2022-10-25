package com.example.osbb.service;

import com.example.osbb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public boolean userExists(Integer userId) {
    return userRepository.existsById(userId);
  }
}
