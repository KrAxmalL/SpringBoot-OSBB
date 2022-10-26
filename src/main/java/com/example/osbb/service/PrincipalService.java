package com.example.osbb.service;

import com.example.osbb.domain.dto.principal.RegisterPrincipalDTO;
import com.example.osbb.domain.dto.principal.UpdateRolesDTO;

public interface PrincipalService {

  void registerPrincipal(RegisterPrincipalDTO registerPrincipalDTO);

  void updateRolesForPrincipal(Integer principalId, UpdateRolesDTO updateRolesDTO);
}
