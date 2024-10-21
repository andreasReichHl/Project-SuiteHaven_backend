package org.example.suiteHaven.services;

import org.example.suiteHaven.dtos.user.UserRequestDto;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.entities.users.UserProfile;
import org.example.suiteHaven.enums.Role;
import org.example.suiteHaven.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final TokenService tokenService;

    private String token;

    public UserService(UserRepository userRepository, RedisService redisService, PasswordEncoder passwordEncoder, EmailService emailService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.redisService = redisService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.tokenService = tokenService;
    }

    public User createNewUser(UserRequestDto dto, Role role) {
        UserProfile userProfile = new UserProfile();
        User user = new User();
        userProfile.setFirstname(dto.firstname());
        userProfile.setLastname(dto.lastname());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setRole(role);
        user.setUserProfile(userProfile);
        userRepository.save(user);
        createMailVerification(user);
        return user;
    }

    public void unlockAccount(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setAccountNonLocked(true);
        userRepository.save(user);
    }

    public void createMailVerification(User user) {
        String token = createJwtToken(user);
        emailService.sendHtmlEmail(user.getEmail(), token);
        redisService.saveValue(token, user.getId());
    }

    public String createJwtToken(User user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        return tokenService.generateToken(authentication);
    }


}
