package org.example.suiteHaven.services.userSpecific;

import org.example.suiteHaven.dtos.user.UserRequestDto;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.entities.users.UserProfile;
import org.example.suiteHaven.enums.Role;
import org.example.suiteHaven.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final RedisService redisService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, RedisService redisService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.redisService = redisService;
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

    public void unlockedAccount(String token){
        try {
            String userMail = redisService.getValue(token);
            if(!userMail.isEmpty()) {
                User user = userRepository.findByEmail(userMail).orElseThrow(() -> new NoSuchElementException("User not found"));
                user.setAccountNonLocked(true);
                userRepository.save(user);
                if(user.isAccountNonLocked()) redisService.deleteValue(token);
            }else {
                throw new RuntimeException("Invalid token or email not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving value from Redis", e);
        }
    }

}
