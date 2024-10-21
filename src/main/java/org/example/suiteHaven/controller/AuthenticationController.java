package org.example.suiteHaven.controller;

import org.example.suiteHaven.dtos.user.LoginRequestDto;
import org.example.suiteHaven.dtos.user.UserRequestDto;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.enums.Role;
import org.example.suiteHaven.exception.UserAlreadyExistsException;
import org.example.suiteHaven.services.AuthenticationService;
import org.example.suiteHaven.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${suiteHaven.api.path}" + "/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signIn")
    public String signIn(Authentication authentication) {
        return authenticationService.token(authentication);
    }

    @PutMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String token){
        if()
    }


}
