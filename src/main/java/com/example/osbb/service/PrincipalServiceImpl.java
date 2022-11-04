package com.example.osbb.service;

import com.example.osbb.domain.dto.principal.RegisterPrincipalDTO;
import com.example.osbb.domain.dto.principal.UpdateRolesDTO;
import com.example.osbb.domain.model.User;
import com.example.osbb.domain.security.Principal;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.exception.EntityValidationException;
import com.example.osbb.repository.PrincipalRepository;
import com.example.osbb.repository.RoleRepository;
import com.example.osbb.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

  private final PrincipalRepository principalRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  private static final String USER_ROLE = "user";

  @Override
  public void registerPrincipal(RegisterPrincipalDTO registerPrincipalDTO) {
    if (principalRepository.existsPrincipalByEmail(registerPrincipalDTO.getEmail())) {
      throw new EntityValidationException("Principal with given email already exists");
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

  @Override
  public void updateRolesForPrincipal(Integer principalId, UpdateRolesDTO updateRolesDTO) {
    Principal principal =
        principalRepository
            .findById(principalId)
            .orElseThrow(
                () -> new EntityNotFoundException("Principal with given id doesn't exist"));

    principal.setRoles(
        updateRolesDTO.getRoles().stream()
            .map(
                roleName ->
                    roleRepository
                        .findRoleByName(roleName)
                        .orElseThrow(
                            () ->
                                new EntityNotFoundException("Role with given name doesn't exist")))
            .collect(Collectors.toList()));

    principalRepository.save(principal);
  }
}
