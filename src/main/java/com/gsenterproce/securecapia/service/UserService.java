package com.gsenterproce.securecapia.service;

import com.gsenterproce.securecapia.domain.User;
import com.gsenterproce.securecapia.dto.UserDTO;

public interface UserService {

    UserDTO createUser(User user);

    UserDTO getUserById(Long id);
}
