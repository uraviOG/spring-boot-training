package com.example.springboot.user.controller.model;

import com.example.springboot.user.service.model.UserType;

import java.util.Objects;

public class UserCreationRequest {

    private String name;

    // TODO : uncomment to enforce this validation
    //@NotBlank(message = "email cannot be blank")
    private String email;

    private UserType type;

    public String getName() {
        return name;
    }

    public UserCreationRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserCreationRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserType getType() {
        return type;
    }

    public UserCreationRequest setType(UserType type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreationRequest that = (UserCreationRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, type);
    }

    @Override
    public String toString() {
        return "UserCreationRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
