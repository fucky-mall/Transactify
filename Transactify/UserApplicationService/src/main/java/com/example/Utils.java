package com.example;

import com.example.dto.CreateUserRequest;
import com.example.dto.GetUserResponse;
import com.example.model.User;

public class Utils {
    public static User convertUserCreateRequest (CreateUserRequest createUserRequest) {
        return User.builder()
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .phone(createUserRequest.getPhone())
                .age(createUserRequest.getAge())
                .build();
    }

    public static GetUserResponse convertToUserResponse(User user) {
        return GetUserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .age(user.getAge())
                .createdOn(user.getCreatedOn())
                .updatedOn(user.getUpdatedOn())
                .build();
    }
}
