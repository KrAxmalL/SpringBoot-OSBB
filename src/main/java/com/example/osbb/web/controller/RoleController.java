package com.example.osbb.web.controller;

import com.example.osbb.domain.dto.role.AddRoleDTO;
import com.example.osbb.domain.dto.role.RoleDetailsDTO;
import com.example.osbb.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Roles", description = "CRUD Management of roles")
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

  private final RoleService roleService;

  @Operation(summary = "Get all available roles")
  @GetMapping
  public Iterable<RoleDetailsDTO> getAllRoles() {
    return roleService.getAllRoles();
  }

  @Operation(summary = "Get role by id. Role with provided id should exists")
  @GetMapping("/{roleId}")
  public RoleDetailsDTO getRoleById(@PathVariable Integer roleId) {
    return roleService.getRoleById(roleId);
  }

  @Operation(summary = "Add new role. Role name must be unique")
  @PostMapping
  public ResponseEntity<?> addRole(@RequestBody @Valid AddRoleDTO addRoleDTO) {
    roleService.addRole(addRoleDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
