package org.example.superbnb.controller;


import org.example.superbnb.dtos.user.UserNewResponseDto;
import org.example.superbnb.dtos.user.UserRequestDto;
import org.example.superbnb.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.example.superbnb.enums.ApiEnums.SUPERBNB_API;

@RestController
@RequestMapping(SUPERBNB_API)
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<UserNewResponseDto> createNewUser(@RequestBody @Validated UserRequestDto dto){
        return ResponseEntity.ok(userService.createNewUser(dto));
    }



}
