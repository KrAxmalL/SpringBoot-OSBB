package com.example.osbb.validation;

import com.example.osbb.domain.dto.role.AddRoleDTO;
import com.example.osbb.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleValidator implements EntityValidator<AddRoleDTO> {

  private final RoleRepository roleRepository;

  @Override
  public boolean isValid(AddRoleDTO entity) {
    return !StringUtils.isBlank(entity.getName())
        && !roleRepository.existsRoleByName(entity.getName());
  }
}
