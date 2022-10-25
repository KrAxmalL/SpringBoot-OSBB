package com.example.osbb.repository;

import com.example.osbb.domain.security.Principal;
import org.springframework.data.repository.CrudRepository;

public interface PrincipalRepository extends CrudRepository<Principal, Integer> {

  boolean existsPrincipalByEmail(String email);
}
