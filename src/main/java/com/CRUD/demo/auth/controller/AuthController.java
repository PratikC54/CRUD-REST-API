package com.CRUD.demo.auth.controller;

import com.CRUD.demo.auth.dto.AuthResponse;
import com.CRUD.demo.auth.dto.LoginRequest;
import com.CRUD.demo.auth.dto.RegisterRequest;
import com.CRUD.demo.auth.services.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = service.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
