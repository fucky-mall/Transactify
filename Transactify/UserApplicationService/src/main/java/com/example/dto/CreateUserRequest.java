package com.example.dto;

import com.example.model.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @Min(18)
    private int age;

    public User toUser() {
        return User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .age(age)
                .build();
    }
}
