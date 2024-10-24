package org.example.suiteHaven.mapper;

import org.example.suiteHaven.dtos.user.UserRequestDto;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.entities.users.UserProfile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<UserRequestDto, User> {

    private final PasswordEncoder passwordEncoder;

    public UserDtoMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User apply(UserRequestDto dto){
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstname(dto.firstname());
        userProfile.setLastname(dto.lastname());

        User user = new User();
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setUserProfile(userProfile);
        return user;
    }
}
