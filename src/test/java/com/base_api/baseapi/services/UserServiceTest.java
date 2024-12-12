package com.base_api.services;

import com.base_api.model.User;
import com.base_api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;  // Mocking the UserRepository

    @InjectMocks
    private UserService userService;  // Injecting the mocked repository into the service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes the mocks
    }

//    @Test
//    void testGetAllUsers() {
//        // Arrange: Preparing mock data
//        User user1 = new User();
//        user1.setName("John");
//        user1.setEmail("john@example.com");
//
//        User user2 = new User();
//        user2.setName("Jane");
//        user2.setEmail("jane@example.com");
//
//        List<User> mockedUsers = Arrays.asList(user1, user2);
//
//        // Stubbing the method
//        when(userRepository.findAll()).thenReturn(mockedUsers);
//
//        // Act: Calling the method we are testing
//        List<User> result = userService.getAllUsers();
//        // Assert: Verifying the result
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals("John", result.get(0).getName());
//        assertEquals("Jane", result.get(1).getName());
//
//        // Verify the interaction with the repository
//        verify(userRepository, times(1)).findAll();
//    }
}
