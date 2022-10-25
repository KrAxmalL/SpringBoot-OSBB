package com.example.osbb.repository;

import com.example.osbb.domain.security.Role;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

  Optional<Role> findRoleByName(String name);
}
