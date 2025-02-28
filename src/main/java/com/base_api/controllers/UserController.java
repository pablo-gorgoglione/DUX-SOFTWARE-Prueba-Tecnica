package com.base_api.controllers;

import com.base_api.dto.user.UserLoginDTO;
import com.base_api.dto.user.UserRegistrationDTO;
import com.base_api.model.common.ResponseDTO;
import com.base_api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ResponseDTO<String> response = ResponseDTO.ofSuccess("User logged successfully.");
        response.setContent(userService.login(dto));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logged/username")
    public ResponseEntity<ResponseDTO<String>> getLoggedUsername() {
        return ResponseEntity.ok(ResponseDTO.ofSuccess(userService.getLoggedUsername()));
    }

}



