package org.example.superbnb.services;

import org.example.superbnb.dtos.user.UserNewResponseDto;
import org.example.superbnb.dtos.user.UserRequestDto;
import org.example.superbnb.entities.users.User;
import org.example.superbnb.entities.users.UserProfile;
import org.example.superbnb.enums.Role;
import org.example.superbnb.exception.UserAlreadyExistsException;
import org.example.superbnb.repositories.UserProfileRepository;
import org.example.superbnb.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    UserRepository userRepository;
    UserProfileRepository userProfileRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserNewResponseDto createNewHost(UserRequestDto dto){
        UserProfile userProfile = new UserProfile();
        User user = new User();
        userProfile.setFirstname(dto.firstname());
        userProfile.setLastname(dto.lastname());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setRole(Role.HOST);
        user.setUserProfile(userProfile);
        try{
            userRepository.save(user);
            return new UserNewResponseDto(userProfile.getName(), user.getEmail(), user.getPassword());
        }catch (DataIntegrityViolationException e){
            throw new UserAlreadyExistsException(user);
        }

    }



}
