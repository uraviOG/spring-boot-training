package com.example.springboot.user.service;

import com.example.springboot.user.controller.model.UserCreationRequest;
import com.example.springboot.user.service.model.User;

public interface UserService {

    User getUserById(String userId);

    void deleteUserById(String userId);

    User createUser(UserCreationRequest userCreationRequest);

}
