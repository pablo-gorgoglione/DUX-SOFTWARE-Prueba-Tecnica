package com.base_api.controllers;

import com.base_api.dto.user.LoginResponseDTO;
import com.base_api.dto.user.UserLoginDTO;
import com.base_api.dto.user.UserRegistrationDTO;
import com.base_api.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO dto) {
        authService.register(dto);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}



