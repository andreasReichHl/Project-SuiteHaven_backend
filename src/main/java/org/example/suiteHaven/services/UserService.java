package org.example.suiteHaven.services;

import org.example.suiteHaven.dtos.user.UserRequestDto;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.entities.users.UserProfile;
import org.example.suiteHaven.enums.Role;
import org.example.suiteHaven.repositories.UserProfileRepository;
import org.example.suiteHaven.repositories.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    private String token;

    public UserService(UserRepository userRepository, RedisService redisService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.redisService = redisService;

        this.passwordEncoder = passwordEncoder;
    }

    public User createNewHost(UserRequestDto dto) {
        UserProfile userProfile = new UserProfile();
        User user = new User();
        userProfile.setFirstname(dto.firstname());
        userProfile.setLastname(dto.lastname());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setRole(Role.HOST);
        user.setUserProfile(userProfile);
        userRepository.save(user);
        createMailVerification(user.getEmail(), user.getId());
        return user;
    }

    public void unlockAccount(long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("User not found"));
        user.setAccountNonLocked(true);
        userRepository.save(user);
    }

    private String createToken(){
        return UUID.randomUUID().toString();
    }

    public void createMailVerification(String mail, Long userId){
        String token = createToken();
        redisService.saveValue(token, userId);
        System.out.println(redisService.getValue(token));
    }




}
