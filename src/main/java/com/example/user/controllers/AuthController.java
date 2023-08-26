package com.example.user.controllers;

import com.example.user.payload.AuthenticationRequest;
import com.example.user.payload.AuthenticationResponse;
import com.example.user.configs.AuthenticationService;
import com.example.user.payload.RegisterRequest;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for user authentication and registration.
 */
@RestController
@RequestMapping("/api/v1/auth")

@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;
    private final UserRepository userrepo;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
