package com.example.osbb.domain.dto.principal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterPrincipalDTO {

  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String patronymic;
}
