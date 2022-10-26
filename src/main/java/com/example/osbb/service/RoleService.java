package com.example.osbb.service;

import com.example.osbb.domain.dto.role.AddRoleDTO;
import com.example.osbb.domain.dto.role.RoleDetailsDTO;

public interface RoleService {

  Iterable<RoleDetailsDTO> getAllRoles();

  RoleDetailsDTO getRoleById(Integer roleId);

  void addRole(AddRoleDTO addRoleDTO);
}
