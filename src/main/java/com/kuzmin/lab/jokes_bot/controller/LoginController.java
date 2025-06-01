package com.kuzmin.lab.jokes_bot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
//@RestController
public class LoginController {

    private final AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<Void> login(String username, String password) {
        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(403).build();
        }
    }

}
