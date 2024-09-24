package org.example.superbnb.controller;

import jakarta.servlet.http.HttpSession;
import org.example.superbnb.dtos.user.UserRequestDto;
import org.example.superbnb.entities.users.User;
import org.example.superbnb.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.example.superbnb.enums.ApiEnums.SUPERBNB_API;

@RestController
@RequestMapping(SUPERBNB_API + "/auth")
public class AuthenticationController {

    AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public void login(){
    }

    @PostMapping("/signup")
    public ResponseEntity<User> createNewUser(@RequestBody @Validated UserRequestDto dto){
        return ResponseEntity.ok(authenticationService.createNewUser(dto));
    }

    @GetMapping("/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }
}
