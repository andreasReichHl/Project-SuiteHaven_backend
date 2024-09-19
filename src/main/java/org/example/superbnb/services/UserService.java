package org.example.superbnb.services;

import org.example.superbnb.dtos.user.UserRequestDto;
import org.example.superbnb.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNewUser(UserRequestDto dto){
        Users
    }
}
