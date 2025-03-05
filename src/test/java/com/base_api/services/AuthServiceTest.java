package com.base_api.services;


import com.base_api.dto.user.LoginResponseDTO;
import com.base_api.dto.user.UserLoginDTO;
import com.base_api.dto.user.UserRegistrationDTO;
import com.base_api.model.User;
import com.base_api.repositories.UserRepository;
import com.base_api.utils.JwtUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Test
    void testGetAllUsers() {
        userRepository.deleteAll();
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setName("Pablo");
        dto.setUsername("pablo1@example.com");
        dto.setPassword("password");

        User user = authService.register(dto);

        Optional<User> userSearched = userRepository.findById(user.getId());

        Assertions.assertTrue(userSearched.isPresent());
        Assertions.assertNotNull(userSearched.get());
    }

    @Test
    void testLogin() {
        UserLoginDTO dtoLogin = new UserLoginDTO();
        dtoLogin.setUsername("test");
        dtoLogin.setPassword("12345");

        LoginResponseDTO loginResponseDTO = authService.login(dtoLogin);
        Assertions.assertNotNull(loginResponseDTO);
        String tokenUserExternalId = JwtUtils.extractUserExternalId(loginResponseDTO.getToken());
        Assertions.assertNotNull(tokenUserExternalId);
    }


}
