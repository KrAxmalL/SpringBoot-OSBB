package com.example.osbb.web.controller;

import com.example.osbb.domain.dto.principal.UpdateRolesDTO;
import com.example.osbb.service.PrincipalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Principals", description = "CRUD management of principals")
@RestController
@RequestMapping("/api/principals")
@RequiredArgsConstructor
public class PrincipalController {

  private final PrincipalService principalService;

  @Operation(summary = "Update roles for principal. Should provide at least one role")
  @PutMapping("/{principalId}")
  public ResponseEntity<?> updateRolesForPrincipal(
      @PathVariable Integer principalId, @RequestBody @Valid UpdateRolesDTO updateRolesDTO) {
    principalService.updateRolesForPrincipal(principalId, updateRolesDTO);
    return ResponseEntity.ok().build();
  }
}
