package org.example.suiteHaven.services;

import org.example.suiteHaven.dtos.user.UserRequestDto;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.entities.users.UserProfile;
import org.example.suiteHaven.enums.Role;
import org.example.suiteHaven.repositories.UserProfileRepository;
import org.example.suiteHaven.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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
//        return new UserNewResponseDto(userProfile.getName(), user.getEmail(), user.getPassword());
        return user;
    }

    public void unlockAccount(long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("User not found"));
        user.setAccountNonLocked(true);
        userRepository.save(user);
    }


}
