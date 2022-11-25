package com.example.osbb.repository;

import com.example.osbb.domain.model.User;
import com.example.osbb.domain.security.Principal;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  Optional<User> findUserByCredentials(Principal credentials);
}
