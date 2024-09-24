package org.example.superbnb.services;

import org.example.superbnb.dtos.user.UserNewResponseDto;
import org.example.superbnb.dtos.user.UserRequestDto;
import org.example.superbnb.entities.User;
import org.example.superbnb.entities.UserProfile;
import org.example.superbnb.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createNewUser(UserRequestDto dto){
        UserProfile userProfile = new UserProfile();
        User user = new User();
        user.setRole(dto.role());
        userProfile.setFirstname(dto.firstname());
        userProfile.setLastname(dto.lastname());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setUserProfile(userProfile);
        return userRepository.save(user);
    }

}
