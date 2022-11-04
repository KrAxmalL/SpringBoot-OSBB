package com.example.osbb.domain.dto.principal;

import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterPrincipalDTO {

  @Email(message = "Email must contain valid email expression")
  private String email;

  @NotBlank(message = "Password must not be empty")
  private String password;

  @NotBlank(message = "First name must not be empty")
  private String firstName;

  @NotBlank(message = "Last name must not be empty")
  private String lastName;

  private String patronymic;
}
