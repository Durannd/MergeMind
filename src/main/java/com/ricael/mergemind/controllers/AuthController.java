package com.ricael. mergemind.controllers;

import com.ricael. mergemind.dto.request. UserLoginRequest;
import com. ricael.mergemind.dto.request.UserRequest;
import com.ricael.mergemind.dto.response.LoginResponse;
import com.ricael. mergemind.dto.response. UserResponse;
import com.ricael.mergemind.services.AuthService;
import com.ricael.mergemind. services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid UserLoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity. ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}