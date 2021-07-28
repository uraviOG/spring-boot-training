package com.example.springboot.user.controller.model;

import com.example.springboot.user.service.model.UserType;

import java.util.Objects;

public class UserInfoResponse {

    // TODO : uncomment to see change in serialised response
    //@JsonProperty("userId")
    private String id;

    private String name;

    private String email;

    private UserType type;

    public String getId() {
        return id;
    }

    public UserInfoResponse setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserInfoResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfoResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserType getType() {
        return type;
    }

    public UserInfoResponse setType(UserType type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoResponse that = (UserInfoResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, type);
    }

    @Override
    public String toString() {
        return "UserInfoResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
