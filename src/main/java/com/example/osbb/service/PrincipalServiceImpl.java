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
import com.example.osbb.validation.PrincipalValidator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

  private final PrincipalRepository principalRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PrincipalValidator principalValidator;

  private static final String USER_ROLE = "user";

  private static final Logger logger = LoggerFactory.getLogger(PrincipalServiceImpl.class);
  private final Marker SERVICE_MARKER = MarkerFactory.getMarker("SERVICE");

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

    logger.info(
        SERVICE_MARKER,
        "Registering user with email={} and password={}",
        registerPrincipalDTO.getEmail(),
        registerPrincipalDTO.getPassword());

    userRepository.save(user);
  }

  @Override
  public void updateRolesForPrincipal(Integer principalId, UpdateRolesDTO updateRolesDTO) {
    Principal principal =
        principalRepository
            .findById(principalId)
            .orElseThrow(
                () -> new EntityNotFoundException("Principal with given id doesn't exist"));

    if (CollectionUtils.isEmpty(updateRolesDTO.getRoles())) {
      throw new EntityValidationException("There must be at least one role");
    }

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
