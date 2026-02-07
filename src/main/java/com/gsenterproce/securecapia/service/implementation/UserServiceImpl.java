package com.gsenterproce.securecapia.service.implementation;

import com.gsenterproce.securecapia.domain.User;
import com.gsenterproce.securecapia.dto.UserDTO;
import com.gsenterproce.securecapia.dtomapper.UserDTOMapper;
import com.gsenterproce.securecapia.repository.UserRepository;
import com.gsenterproce.securecapia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;

    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }

    @Override
    public UserDTO getUserById(Long id) {
        return UserDTOMapper.fromUser(userRepository.get(id));
    }
}
