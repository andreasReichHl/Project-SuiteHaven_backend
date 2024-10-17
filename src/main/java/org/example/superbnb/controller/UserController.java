package org.example.superbnb.controller;


import org.example.superbnb.dtos.user.UserNewResponseDto;
import org.example.superbnb.dtos.user.UserRequestDto;
import org.example.superbnb.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${suiteHaven.api.path}"+"/auth")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/newHost")
    public ResponseEntity<UserNewResponseDto> createNewUser(@RequestBody @Validated UserRequestDto dto){
        return ResponseEntity.ok(userService.createNewHost(dto));
    }



}
