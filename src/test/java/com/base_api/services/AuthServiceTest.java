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

import java.util.List;
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
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setName("Pablo");
        dto.setUsername("pablo@example.com");
        dto.setPassword("password");

        User user = authService.register(dto);

        Optional<User> userSearched = userRepository.findById(user.getId());

        Assertions.assertTrue(userSearched.isPresent());
        Assertions.assertNotNull(userSearched.get());
    }

    @Test
    void testLogin() {
        UserRegistrationDTO dtoRegistration = new UserRegistrationDTO();
        dtoRegistration.setName("Pablo");
        dtoRegistration.setUsername("pablo@example.com");
        dtoRegistration.setPassword("password");

        User registeredUser = authService.register(dtoRegistration);
        Assertions.assertNotNull(registeredUser);

        UserLoginDTO dtoLogin = new UserLoginDTO();
        dtoLogin.setUsername(dtoRegistration.getUsername());
        dtoLogin.setPassword(dtoRegistration.getPassword());

        LoginResponseDTO loginResponseDTO = authService.login(dtoLogin);
        Assertions.assertNotNull(loginResponseDTO);
        String tokenUserExternalId = JwtUtils.extractUserExternalId(loginResponseDTO.getToken());
        Assertions.assertEquals(registeredUser.getExternalId(), tokenUserExternalId);
    }


}
