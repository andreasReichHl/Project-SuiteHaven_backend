package org.example.suiteHaven.services.userSpecific;

import org.example.suiteHaven.dtos.user.UserRequestDto;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.entities.users.UserProfile;
import org.example.suiteHaven.enums.Role;
import org.example.suiteHaven.mapper.UserDtoMapper;
import org.example.suiteHaven.repositories.UserRepository;
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
    private final UserDtoMapper mapper;

    private String token;

    public UserService(UserRepository userRepository, RedisService redisService, PasswordEncoder passwordEncoder, EmailService emailService, TokenService tokenService, UserDtoMapper mapper) {
        this.userRepository = userRepository;
        this.redisService = redisService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    public User createNewUser(UserRequestDto dto, Role role) {
        User user = mapper.apply(dto);
        user.setRole(role);
        userRepository.save(user);
        createMailVerification(user);
        return user;
    }

    public void createMailVerification(User user) {
        String token = createJwtToken(user);
        String userMail = user.getEmail();
        emailService.sendHtmlEmail(userMail, token);
        redisService.saveValue(token, userMail);
        System.out.println(redisService.getValue(token));
    }

    public String createJwtToken(User user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        return tokenService.generateToken(authentication);
    }


}
