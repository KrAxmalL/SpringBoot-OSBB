package com.example.osbb.domain.mapper;

import com.example.osbb.domain.dto.role.RoleDetailsDTO;
import com.example.osbb.domain.security.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  RoleDetailsDTO toRoleDetailsDTO(Role role);
}
