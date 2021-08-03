package com.example.springboot.user.service.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum UserType {

    ADMIN("admin"),
    FREE("free"),
    PAID("paid");

    private String value;

    UserType(String type) {
        this.value = type;
    }

    // TODO : comment this and see how the UserType values change in response
    @JsonValue
    public String getValue() {
        return this.value;
    }

    public static Optional<UserType> getUserType(String type) {
        return Arrays.stream(UserType.values()).filter(chatType -> chatType.getValue().equalsIgnoreCase(type))
                .findFirst();
    }

}
