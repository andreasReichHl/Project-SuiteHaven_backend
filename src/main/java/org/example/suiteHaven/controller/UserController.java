package org.example.suiteHaven.controller;


import org.example.suiteHaven.dtos.user.UserRequestDto;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.exception.UserAlreadyExistsException;
import org.example.suiteHaven.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${suiteHaven.api.path}" + "/auth")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/newHost")
    public ResponseEntity<User> createNewUser(@RequestBody @Validated UserRequestDto dto) {
        try {
            return ResponseEntity.ok(userService.createNewHost(dto));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/unlocked")
    public ResponseEntity<Void> unlockAccount(@RequestParam long userId){
        userService.unlockAccount(userId);
        return ResponseEntity.ok().build();
    }
}
