package org.example.suiteHaven.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectController {

    @GetMapping("/user")
    public ResponseEntity<String> getUsername(Authentication authentication){
        return ResponseEntity.ok(authentication.getName());
    }
}
