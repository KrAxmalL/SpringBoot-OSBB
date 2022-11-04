package com.example.osbb.service;

import com.example.osbb.domain.dto.role.AddRoleDTO;
import com.example.osbb.domain.dto.role.RoleDetailsDTO;
import com.example.osbb.domain.mapper.RoleMapper;
import com.example.osbb.domain.security.Role;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;

  @Override
  public Iterable<RoleDetailsDTO> getAllRoles() {
    final List<RoleDetailsDTO> roles = new ArrayList<>();
    roleRepository.findAll().forEach(role -> roles.add(roleMapper.toRoleDetailsDTO(role)));
    return roles;
  }

  @Override
  public RoleDetailsDTO getRoleById(Integer roleId) {
    return roleRepository
        .findById(roleId)
        .map(roleMapper::toRoleDetailsDTO)
        .orElseThrow(() -> new EntityNotFoundException("Role with given id doesn't exist"));
  }

  @Override
  public void addRole(AddRoleDTO addRoleDTO) {
    Role role = Role.builder().name(addRoleDTO.getName()).build();
    roleRepository.save(role);
  }
}
