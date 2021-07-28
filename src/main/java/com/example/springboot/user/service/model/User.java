package com.example.springboot.user.service.model;

import com.example.springboot.user.respository.model.UserDAO;

import java.util.Objects;

public class User {

    private String id;

    private String name;

    private String email;

    private UserType type;

    public static User from(UserDAO userDAO) {
        return new User().setId(userDAO.getId())
                .setName(userDAO.getName())
                .setEmail(userDAO.getEmail())
                .setType(UserType.getUserType(
                        userDAO.getType()).orElseThrow(() ->
                        new IllegalStateException("UserType[" + userDAO.getType() + "] is not a valid userType")
                        )
                );
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public UserType getType() {
        return type;
    }

    public User setType(UserType type) {
        this.type = type;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                type == user.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, type);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
