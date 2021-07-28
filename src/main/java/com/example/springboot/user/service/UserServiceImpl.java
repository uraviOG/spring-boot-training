package com.example.springboot.user.service;

import com.example.springboot.user.controller.model.UserCreationRequest;
import com.example.springboot.user.exceptions.UserNotFoundException;
import com.example.springboot.user.respository.UserRepository;
import com.example.springboot.user.respository.model.UserDAO;
import com.example.springboot.user.service.model.User;
import com.example.springboot.user.service.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

@Component
@Validated
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String userId) {
        Optional<UserDAO> userDAO = userRepository.getUserById(userId);
        return userDAO.map(User::from)
                .orElseThrow(() -> new UserNotFoundException("User with id[" + userId + "] not found"));
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteUser(userRepository.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id[" + userId + "] not found")));
    }

    @Override
    public User createUser(final UserCreationRequest userCreationRequest) {
        String id = UUID.randomUUID().toString();
        UserType userType = (userCreationRequest.getType() == null) ? UserType.FREE : userCreationRequest.getType();

        UserDAO userDAO = userRepository.createUser(new UserDAO().setId(id)
                .setName(userCreationRequest.getName())
                .setEmail(userCreationRequest.getEmail())
                .setType(userType.getValue()));

        return User.from(userDAO);
    }
}
