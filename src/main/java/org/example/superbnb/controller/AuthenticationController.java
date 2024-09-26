package org.example.superbnb.controller;

import org.example.superbnb.dtos.user.LoginRequestDto;
import org.example.superbnb.dtos.user.UserRequestDto;
import org.example.superbnb.entities.users.User;
import org.example.superbnb.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.example.superbnb.enums.ApiEnums.SUPERBNB_API;

@RestController
@RequestMapping(SUPERBNB_API + "/auth")
public class AuthenticationController {

    final AuthenticationService authenticationService;
    final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationService authenticationService, AuthenticationManager authenticationManager) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/signup")
    public ResponseEntity<User> createNewUser(@RequestBody @Validated UserRequestDto dto) {
        return ResponseEntity.ok(authenticationService.createNewUser(dto));
    }

    @PostMapping("/signIn")
    public String signIn(Authentication authentication) {
        return authenticationService.token(authentication);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String token = authenticationService.token(authentication);

        // Rückgabe des Tokens in einem JSON-Objekt
        Map<String, String> response = new HashMap<>();
        response.put("token", token); // Setze den Token in eine Map

        // Gibt die Map als JSON zurück
        return ResponseEntity.ok(response);
    }
}
