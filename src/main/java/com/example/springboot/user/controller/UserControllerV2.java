package com.example.springboot.user.controller;

import com.example.springboot.user.controller.model.UserCreationRequest;
import com.example.springboot.user.controller.model.UserInfoResponse;
import com.example.springboot.user.service.UserService;
import com.example.springboot.user.service.UserServiceImpl;
import com.example.springboot.user.service.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v2/users")
public class UserControllerV2 {

    private static final Logger logger = LogManager.getLogger(UserControllerV1.class);

    private final UserService userService;

    @Autowired
    public UserControllerV2(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserInfoResponse getUser(@PathVariable String id) {
        // fetch user details object for the given id
        User user = userService.getUserById(id);
        return new UserInfoResponse().setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setType(user.getType());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<UserInfoResponse> getAllUsers() {
        // fetch user details object for the given id
        List<User> usersList = userService.getUsers();

        return usersList.stream().map(user ->
                new UserInfoResponse().setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setType(user.getType())).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUser(@PathVariable String id) {
        logger.info("deleting user with id :: " + id);
        userService.deleteUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfoResponse createUser(@Valid @RequestBody UserCreationRequest userCreationRequest) {
        // create new user
        logger.info("creating new user with request :: " + userCreationRequest);
        User user = userService.createUser(userCreationRequest);
        return new UserInfoResponse().setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setType(user.getType());
    }

}
