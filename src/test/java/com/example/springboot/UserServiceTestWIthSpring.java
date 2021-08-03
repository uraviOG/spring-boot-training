package com.example.springboot;

import com.example.springboot.user.exceptions.UserNotFoundException;
import com.example.springboot.user.respository.UserRepository;
import com.example.springboot.user.respository.model.UserDAO;
import com.example.springboot.user.service.UserService;
import com.example.springboot.user.service.UserServiceImpl;
import com.example.springboot.user.service.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTestWIthSpring {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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

    // TODO : we need to provide a test configuration in this case
    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public UserService userService(UserRepository userRepository) {
            return new UserServiceImpl(userRepository);
        }
    }

}
