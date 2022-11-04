package com.example.osbb.web.controller;

import com.example.osbb.domain.dto.role.AddRoleDTO;
import com.example.osbb.domain.dto.role.RoleDetailsDTO;
import com.example.osbb.service.RoleService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

  private final RoleService roleService;

  @GetMapping
  public Iterable<RoleDetailsDTO> getAllRoles() {
    return roleService.getAllRoles();
  }

  @GetMapping("/{roleId}")
  public RoleDetailsDTO getRoleById(@PathVariable Integer roleId) {
    return roleService.getRoleById(roleId);
  }

  @PostMapping
  public ResponseEntity<?> addRole(@RequestBody @Valid AddRoleDTO addRoleDTO) {
    roleService.addRole(addRoleDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
