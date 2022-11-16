package com.example.osbb.service;

import com.example.osbb.domain.dto.jwt.JwtToken;
import com.example.osbb.domain.dto.principal.LoginPrincipalDTO;
import com.example.osbb.domain.dto.principal.RegisterPrincipalDTO;

public interface AuthenticationService {

  JwtToken loginPrincipal(LoginPrincipalDTO loginPrincipalDTO);

  void registerPrincipal(RegisterPrincipalDTO registerPrincipalDTO);
}
