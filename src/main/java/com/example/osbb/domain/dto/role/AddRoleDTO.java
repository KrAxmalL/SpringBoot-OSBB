package com.example.osbb.domain.dto.role;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddRoleDTO {

  @NotBlank(message = "Name must not be empty")
  String name;
}
