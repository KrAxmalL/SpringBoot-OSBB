package com.example.osbb.domain.dto.principal;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRolesDTO {

  @NotNull(message = "Role list must not be null")
  @Size(min = 1, message = "Role list must contain at least 1 role")
  private List<String> roles;
}
