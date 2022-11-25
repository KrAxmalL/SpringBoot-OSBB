package com.example.osbb.service;

import com.example.osbb.domain.model.User;

public interface UserService {

  boolean userExists(Integer userId);

  User getUserFromSecurityContext();
}
