package com.example.osbb.service;

import com.example.osbb.domain.dto.jwt.JwtToken;
import com.example.osbb.domain.dto.principal.LoginPrincipalDTO;
import com.example.osbb.domain.dto.principal.RegisterPrincipalDTO;
import com.example.osbb.domain.model.User;
import com.example.osbb.domain.security.Principal;
import com.example.osbb.exception.EntityValidationException;
import com.example.osbb.repository.PrincipalRepository;
import com.example.osbb.repository.RoleRepository;
import com.example.osbb.repository.UserRepository;
import com.example.osbb.security.jwt.JWTManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private static final String USER_ROLE = "user";

  private final AuthenticationManager authenticationManager;
  private final JWTManager jwtManager;
  private final PasswordEncoder passwordEncoder;

  private final PrincipalRepository principalRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  public JwtToken loginPrincipal(LoginPrincipalDTO loginPrincipalDTO) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            loginPrincipalDTO.getEmail(), loginPrincipalDTO.getPassword());
    Principal principal =
        (Principal) authenticationManager.authenticate(authenticationToken).getPrincipal();

    String accessToken = jwtManager.getAccessToken(principal);
    return new JwtToken(accessToken);
  }

  @Override
  public void registerPrincipal(RegisterPrincipalDTO registerPrincipalDTO) {
    if (principalRepository.existsPrincipalByEmail(registerPrincipalDTO.getEmail())) {
      throw new EntityValidationException("Principal with given email already exists");
    }

    Principal principal =
        Principal.builder()
            .email(registerPrincipalDTO.getEmail())
            .password(passwordEncoder.encode(registerPrincipalDTO.getPassword()))
            .roles(List.of(roleRepository.findRoleByName(USER_ROLE).get()))
            .build();
    principalRepository.save(principal);

    User user =
        User.builder()
            .firstName(registerPrincipalDTO.getFirstName())
            .lastName(registerPrincipalDTO.getLastName())
            .patronymic(registerPrincipalDTO.getPatronymic())
            .credentials(principal)
            .advertisements(List.of())
            .build();

    userRepository.save(user);
  }
}
