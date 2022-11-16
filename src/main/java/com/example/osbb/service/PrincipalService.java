package com.example.osbb.service;

import com.example.osbb.domain.dto.principal.UpdateRolesDTO;
import com.example.osbb.domain.security.Principal;

public interface PrincipalService {

  Principal getPrincipalByEmail(String email);

  void updateRolesForPrincipal(Integer principalId, UpdateRolesDTO updateRolesDTO);
}
