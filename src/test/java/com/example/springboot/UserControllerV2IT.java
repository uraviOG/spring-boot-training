package com.example.springboot;

import com.example.springboot.user.controller.ApiExceptionHandlerAdvice;
import com.example.springboot.user.controller.model.UserInfoResponse;
import com.example.springboot.user.service.model.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // This will start the application and create
public class UserControllerV2IT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetUserById() throws Exception {
        // This is an existing user in our Db pre-created, you can use any existing userId from your own users table.
        String userId = "d829b84b-5507-435a-8b3e-ec3cdbc52574";

        UserInfoResponse expectedResponse = new UserInfoResponse()
                .setId(userId)
                .setName("test-user-11")
                .setEmail("test-user-11@test.com")
                .setType(UserType.PAID);

        ResponseEntity<UserInfoResponse> response = restTemplate
                .getForEntity("http://localhost:8080/v2/users/" + userId, UserInfoResponse.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testGetUserByIdNotFound() throws Exception {
        String userId = "12345";
        ApiExceptionHandlerAdvice.ErrorResponse expectedResponse =
                new ApiExceptionHandlerAdvice.ErrorResponse("User with id[" + userId + "] not found", HttpStatus.NOT_FOUND.value());

        ResponseEntity<ApiExceptionHandlerAdvice.ErrorResponse> response = restTemplate
                .getForEntity("http://localhost:8080/v2/users/" + userId,
                        ApiExceptionHandlerAdvice.ErrorResponse.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(expectedResponse, response.getBody());

    }

}
