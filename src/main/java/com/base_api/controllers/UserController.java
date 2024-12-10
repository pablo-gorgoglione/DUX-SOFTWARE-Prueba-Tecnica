package com.base_api.controllers;

import com.base_api.model.User;
import com.base_api.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public List<User> sayHello() {
        return userService.getAllUsers();
    }
}
