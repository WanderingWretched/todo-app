package com.example.testtodoapp.controller;

import com.example.testtodoapp.dto.user.FindUserRequest;
import com.example.testtodoapp.dto.user.UserDto;
import com.example.testtodoapp.exceptions.UserNotFoundException;
import com.example.testtodoapp.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    public List<UserDto> getUser() {
        return userService.geAllUsers();
    }

    @GetMapping("/user/{userId}")
    public UserDto findUserById(@Valid @NotNull @PathVariable("userId") Long userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @GetMapping("/user")
    public List<UserDto> findUserFirstName(@RequestBody @Valid FindUserRequest findUserRequest) {
        return userService.getByFirstName(findUserRequest);
    }

    @PostMapping("/user/{userId}")
    public UserDto updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid UserDto user) throws UserNotFoundException {
        return userService.update(userId, user);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.delete(userId);
    }
}
