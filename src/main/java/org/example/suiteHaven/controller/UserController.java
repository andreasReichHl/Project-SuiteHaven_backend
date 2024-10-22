package org.example.suiteHaven.controller;


import org.example.suiteHaven.services.userSpecific.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${suiteHaven.api.path}" + "/auth")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


}
