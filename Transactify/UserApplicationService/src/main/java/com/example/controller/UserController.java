package com.example.controller;

import com.example.Utils;
import com.example.dto.CreateUserRequest;
import com.example.dto.GetUserResponse;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public void createUser(@RequestBody @Valid CreateUserRequest createUserRequest) throws JsonProcessingException {
        userService.createUser(Utils.convertUserCreateRequest(createUserRequest));
    }

    @GetMapping("/user/{userId}")
    public GetUserResponse getUser(@PathVariable("userId") int userId) throws Exception{
        return userService.get(userId);
    }
}
