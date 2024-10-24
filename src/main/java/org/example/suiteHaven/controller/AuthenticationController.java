package org.example.suiteHaven.controller;

import org.example.suiteHaven.dtos.JwtDto;
import org.example.suiteHaven.dtos.TokenRequestDto;
import org.example.suiteHaven.services.userSpecific.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("${suiteHaven.api.path}" + "/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtDto> signIn(Authentication authentication) {
        return ResponseEntity.ok(new JwtDto(authenticationService.token(authentication)));


    }

    @PutMapping("/unlocked")
    public ResponseEntity<?> verifyEmail(@RequestBody TokenRequestDto dto){
      try{
          authenticationService.unlockedAccount(dto.token());
          return ResponseEntity.ok("Account successfully unlocked.");
      }catch (NoSuchElementException e){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid token or user not found");
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error unlocking account.");
      }
    }


}
