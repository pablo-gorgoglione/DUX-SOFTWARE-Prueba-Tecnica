package com.base_api.controllers;

import com.base_api.dto.user.UserLoginDTO;
import com.base_api.dto.user.UserRegistrationDTO;
import com.base_api.model.common.ResponseDTO;
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
  public ResponseEntity<ResponseDTO<String>> register(@RequestBody UserRegistrationDTO dto) {
    userService.register(dto);
    return ResponseEntity.ok(ResponseDTO.ofSuccess("User registered successfully."));
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseDTO<String>> login(@RequestBody UserLoginDTO dto) {
    userService.login(dto);
    return ResponseEntity.ok(ResponseDTO.ofSuccess("User logged successfully."));
  }

}



