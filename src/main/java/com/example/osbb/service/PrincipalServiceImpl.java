package com.example.osbb.service;

import com.example.osbb.domain.dto.principal.RegisterPrincipalDTO;
import com.example.osbb.domain.model.User;
import com.example.osbb.domain.security.Principal;
import com.example.osbb.exception.EntityValidationException;
import com.example.osbb.repository.PrincipalRepository;
import com.example.osbb.repository.RoleRepository;
import com.example.osbb.repository.UserRepository;
import com.example.osbb.validation.PrincipalValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

  private final PrincipalRepository principalRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PrincipalValidator principalValidator;

  private static final String USER_ROLE = "user";

  @Override
  public void registerPrincipal(RegisterPrincipalDTO registerPrincipalDTO) {
    if (!principalValidator.isValid(registerPrincipalDTO)) {
      throw new EntityValidationException("Principal is not valid");
    }

    Principal principal =
        Principal.builder()
            .email(registerPrincipalDTO.getEmail())
            .password(registerPrincipalDTO.getPassword())
            .roles(List.of(roleRepository.findRoleByName(USER_ROLE).get()))
            .build();
    principalRepository.save(principal);

    User user =
        User.builder()
            .firstName(registerPrincipalDTO.getFirstName())
            .lastName(registerPrincipalDTO.getLastName())
            .patronymic(registerPrincipalDTO.getPatronymic())
            .credentials(principal)
            .advertisements(List.of())
            .build();
    userRepository.save(user);
  }
}
