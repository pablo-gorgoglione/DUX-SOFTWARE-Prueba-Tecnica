package com.base_api.services;

import com.base_api.dto.user.UserLoginDTO;
import com.base_api.dto.user.UserRegistrationDTO;
import com.base_api.model.User;
import com.base_api.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testGetAllUsers() {
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setName("Pablo");
        dto.setEmail("pablo@example.com");
        dto.setPassword("password");

        User user = userService.register(dto);

        Optional<User> userSearched = userRepository.findById(user.getId());

        Assertions.assertTrue(userSearched.isPresent());
        Assertions.assertNotNull(userSearched.get());
    }

    @Test
    void testFindAll() {
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setName("Pablo");
        dto.setEmail("pablo@example.com");
        dto.setPassword("password");

        UserRegistrationDTO dto2 = new UserRegistrationDTO();
        dto2.setName("Pablo2");
        dto2.setEmail("pablo2@example.com");
        dto2.setPassword("asdfgh");

        userService.register(dto);
        userService.register(dto2);

        List<User> users = userService.findAll();
        Assertions.assertEquals(2, users.size());
    }

    @Test
    void testLogin() {
        UserRegistrationDTO dtoRegistration = new UserRegistrationDTO();
        dtoRegistration.setName("Pablo");
        dtoRegistration.setEmail("pablo@example.com");
        dtoRegistration.setPassword("password");

        User registeredUser = userService.register(dtoRegistration);
        Assertions.assertNotNull(registeredUser);

        UserLoginDTO dtoLogin = new UserLoginDTO();
        dtoLogin.setEmail(dtoRegistration.getEmail());
        dtoLogin.setPassword(dtoRegistration.getPassword());

        User loggedUser = userService.login(dtoLogin);
        Assertions.assertNotNull(loggedUser);

        Assertions.assertEquals(registeredUser.getId(), loggedUser.getId());
    }


}
