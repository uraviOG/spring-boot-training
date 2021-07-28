package com.example.springboot.user.respository.model;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UserDAO {

    @NotBlank
    private String id;

    private String name;

    private String email;

    private String type;

    public String getId() {
        return id;
    }

    public UserDAO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDAO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDAO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getType() {
        return type;
    }

    public UserDAO setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDAO userDAO = (UserDAO) o;
        return Objects.equals(id, userDAO.id) &&
                Objects.equals(name, userDAO.name) &&
                Objects.equals(email, userDAO.email) &&
                Objects.equals(type, userDAO.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, type);
    }

    @Override
    public String toString() {
        return "UserDAO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
