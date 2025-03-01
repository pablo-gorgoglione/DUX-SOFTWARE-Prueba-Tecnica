package com.base_api;

import com.base_api.dto.user.UserRegistrationDTO;
import com.base_api.services.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AuthService authService;

    public DataInitializer(AuthService authService) {
        this.authService = authService;
    }


    @Override
    public void run(String... args) {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUsername("test");
        userRegistrationDTO.setName("test");
        userRegistrationDTO.setPassword("12345");

        authService.register(userRegistrationDTO);
        System.out.println("Usuario insertado con éxito");
    }
}