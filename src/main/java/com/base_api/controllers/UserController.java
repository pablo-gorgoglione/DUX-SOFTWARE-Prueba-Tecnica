package com.base_api.controllers;

import com.base_api.dto.user.UserLoginDTO;
import com.base_api.dto.user.UserRegistrationDTO;
import com.base_api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody UserRegistrationDTO dto) {
    try {
      userService.register(dto);
      return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserLoginDTO dto) {
    boolean logged = userService.login(dto);
    return logged
        ? ResponseEntity.status(HttpStatus.CREATED).body("User logged successfully.")
        : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login failed");
  }

}



