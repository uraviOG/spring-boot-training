package com.example.springboot.user.controller;

import com.example.springboot.user.controller.model.UserCreationRequest;
import com.example.springboot.user.controller.model.UserInfoResponse;
import com.example.springboot.user.service.model.UserType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserControllerV1 {

    private static final Logger logger = LogManager.getLogger(UserControllerV1.class);

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserInfoResponse getUser(@PathVariable String id) {
        // fetch user details object for the given id
        return new UserInfoResponse().setId(id).setName("test-user").setType(UserType.ADMIN);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUser(@PathVariable String id) {
        logger.info("deleting user with id :: " + id);
        // delete user details for the given id
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfoResponse createUser(@RequestBody UserCreationRequest userCreationRequest) {
        // create new user
        logger.info("creating new user with request :: " + userCreationRequest);
        String userId = RandomStringUtils.randomAlphanumeric(10);
        return new UserInfoResponse().setId(userId)
                .setName(userCreationRequest.getName())
                .setType(userCreationRequest.getType());
    }

}
