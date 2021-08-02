package com.example.springboot;

import com.example.springboot.user.exceptions.UserNotFoundException;
import com.example.springboot.user.respository.UserRepository;
import com.example.springboot.user.respository.model.UserDAO;
import com.example.springboot.user.service.UserService;
import com.example.springboot.user.service.UserServiceImpl;
import com.example.springboot.user.service.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testGetUserById() {
        String userId = "12345";
        when(userRepository.getUserById(userId)).thenReturn(Optional.of(new UserDAO()
                .setId(userId)
                .setName("test-name")
                .setEmail("test@test.com")
                .setType("admin")));

        User returnedUser = userService.getUserById(userId);
        Assertions.assertEquals(returnedUser.getId(), userId);
        Assertions.assertEquals(returnedUser.getName(), "test-name");
    }

    @Test
    public void testGetUserByIdNotFound() {
        String userId = "12345";
        when(userRepository.getUserById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }

}
