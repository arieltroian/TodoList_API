package com.arieltroian.todolist.controller;

import com.arieltroian.todolist.dto.CreateUserRequest;
import com.arieltroian.todolist.entity.User;
import com.arieltroian.todolist.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }
}
