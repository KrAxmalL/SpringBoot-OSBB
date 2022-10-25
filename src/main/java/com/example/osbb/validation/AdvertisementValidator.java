package com.example.osbb.validation;

import com.example.osbb.domain.dto.advertisement.AddAdvertisementDTO;
import com.example.osbb.service.UserService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdvertisementValidator implements EntityValidator<AddAdvertisementDTO> {

  private final UserService userService;

  @Override
  public boolean isValid(AddAdvertisementDTO entity) {
    return !StringUtils.isBlank(entity.getTitle())
        && !StringUtils.isBlank(entity.getContent())
        && !Objects.isNull(entity.getAuthorId())
        && userService.userExists(entity.getAuthorId());
  }
}
