package com.example.osbb.web.controller;

import com.example.osbb.domain.dto.principal.UpdateRolesDTO;
import com.example.osbb.service.PrincipalService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/principals")
@RequiredArgsConstructor
public class PrincipalController {

  private final PrincipalService principalService;

  @PutMapping("/{principalId}")
  public ResponseEntity<?> updateRolesForPrincipal(
      @PathVariable Integer principalId, @RequestBody @Valid UpdateRolesDTO updateRolesDTO) {
    principalService.updateRolesForPrincipal(principalId, updateRolesDTO);
    return ResponseEntity.ok().build();
  }
}
