package com.example.springboot;

import com.example.springboot.user.controller.ApiExceptionHandlerAdvice;
import com.example.springboot.user.controller.UserControllerV2;
import com.example.springboot.user.controller.model.UserInfoResponse;
import com.example.springboot.user.exceptions.UserNotFoundException;
import com.example.springboot.user.service.UserService;
import com.example.springboot.user.service.model.User;
import com.example.springboot.user.service.model.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserControllerV2.class)
public class UserControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetUserById() throws Exception {
        String userId = "12345";

        UserInfoResponse expectedResponse = new UserInfoResponse()
                .setId(userId)
                .setName("test-name")
                .setEmail("test@test.com")
                .setType(UserType.ADMIN);

        when(userService.getUserById(userId)).thenReturn(new User()
                .setId(userId)
                .setName("test-name")
                .setEmail("test@test.com")
                .setType(UserType.ADMIN));

        mockMvc.perform(MockMvcRequestBuilders.get("/v2/users/" + userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));

    }

    @Test
    public void testGetUserByIdNotFound() throws Exception {
        String userId = "12345";
        when(userService.getUserById(userId)).thenThrow(new UserNotFoundException("user not found"));

        ApiExceptionHandlerAdvice.ErrorResponse expectedResponse =
                new ApiExceptionHandlerAdvice.ErrorResponse("user not found", HttpStatus.NOT_FOUND.value());

        mockMvc.perform(MockMvcRequestBuilders.get("/v2/users/" + userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));

    }


}
