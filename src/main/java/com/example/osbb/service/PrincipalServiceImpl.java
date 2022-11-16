package com.example.osbb.service;

import com.example.osbb.domain.dto.principal.UpdateRolesDTO;
import com.example.osbb.domain.security.Principal;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.repository.PrincipalRepository;
import com.example.osbb.repository.RoleRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

  private final PrincipalRepository principalRepository;
  private final RoleRepository roleRepository;

  @Override
  public Principal getPrincipalByEmail(String email) {
    return principalRepository
        .getPrincipalByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("Principal with given email doesn't exist"));
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
