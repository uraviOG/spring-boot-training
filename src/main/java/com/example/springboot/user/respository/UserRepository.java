package com.example.springboot.user.respository;

import com.example.springboot.user.respository.model.UserDAO;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<UserDAO> getUserById(@NotBlank String id);

    List<UserDAO> getUsers();

    void deleteUser(UserDAO userDAO);

    UserDAO createUser(UserDAO userDAO);

}
