package org.example.superbnb.services;

import org.example.superbnb.dtos.user.UserRequestDto;
import org.example.superbnb.entities.users.User;
import org.example.superbnb.entities.users.UserProfile;
import org.example.superbnb.enums.Role;
import org.example.superbnb.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User createNewUser(UserRequestDto dto){
        UserProfile userProfile = new UserProfile();
        User user = new User();
        user.setRole(Role.HOST);
        userProfile.setFirstname(dto.firstname());
        userProfile.setLastname(dto.lastname());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setUserProfile(userProfile);
        return userRepository.save(user);
    }

    public String token(Authentication authentication){
        return tokenService.generateToken(authentication);
    }

}
