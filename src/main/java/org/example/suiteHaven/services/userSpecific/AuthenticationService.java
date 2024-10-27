package org.example.suiteHaven.services.userSpecific;

import org.example.suiteHaven.dtos.user.UserRequestDto;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.entities.users.UserProfile;
import org.example.suiteHaven.enums.Role;
import org.example.suiteHaven.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final RedisService redisService;
    private final JwtDecoder jwtDecoder;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, RedisService redisService, JwtDecoder jwtDecoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.redisService = redisService;
        this.jwtDecoder = jwtDecoder;
    }

    public User createNewUser(UserRequestDto dto) {
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

    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

    public void unlockedAccount(String token) {
        try {
            String userMail = redisService.getValue(token);
            if (!userMail.isEmpty()) {
                User user = userRepository.findByEmail(userMail).orElseThrow(() -> new NoSuchElementException("User not found"));
                user.setAccountNonLocked(true);
                userRepository.save(user);
                if (user.isAccountNonLocked()) redisService.deleteValue(token);
            } else {
                throw new RuntimeException("Invalid token or email not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving value from Redis", e);
        }
    }

    public Authentication getAuthentication(String token) {
        if (token != null) {
            Jwt jwt = jwtDecoder.decode(token);
            String username = jwt.getSubject();
            Collection<? extends GrantedAuthority> authorities = extractAuthorities(jwt.getClaims());
            return new UsernamePasswordAuthenticationToken(username, token, authorities);
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> extractAuthorities(Map<String, Object> claims) {
        List<String> roles = (List<String>) claims.get("scope");
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public boolean validateToken(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        Instant expiresTime = jwt.getExpiresAt();
        if (expiresTime != null) {
            return expiresTime.isAfter(Instant.now());
        }
        return false;
    }

}
