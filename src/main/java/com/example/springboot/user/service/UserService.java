package com.example.springboot.user.service;

import com.example.springboot.user.controller.model.UserCreationRequest;
import com.example.springboot.user.exceptions.UserNotFoundException;
import com.example.springboot.user.service.model.User;

import java.util.List;

public interface UserService {

    User getUserById(String userId) throws UserNotFoundException;

    List<User> getUsers();

    void deleteUserById(String userId);

    User createUser(UserCreationRequest userCreationRequest);

}
