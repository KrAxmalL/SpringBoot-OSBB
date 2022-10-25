package com.example.osbb.validation;

import com.example.osbb.domain.dto.principal.RegisterPrincipalDTO;
import com.example.osbb.repository.PrincipalRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrincipalValidator implements EntityValidator<RegisterPrincipalDTO> {

  private final PrincipalRepository principalRepository;

  @Override
  public boolean isValid(RegisterPrincipalDTO entity) {
    return !StringUtils.isBlank(entity.getEmail())
        && !StringUtils.isBlank(entity.getPassword())
        && !StringUtils.isBlank(entity.getFirstName())
        && !StringUtils.isBlank(entity.getLastName())
        && !principalRepository.existsPrincipalByEmail(entity.getEmail());
  }
}
