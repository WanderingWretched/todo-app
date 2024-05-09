package com.example.testtodoapp.service;

import com.example.testtodoapp.dto.user.FindUserRequest;
import com.example.testtodoapp.dto.user.UserDto;
import com.example.testtodoapp.exceptions.UserNotFoundException;

import java.util.List;


public interface UserService {
    List<UserDto> geAllUsers();

    UserDto getUser(Long userId) throws UserNotFoundException;

    List<UserDto> getByFirstName(FindUserRequest findUserRequest);

    UserDto update(Long userId, UserDto user) throws UserNotFoundException;

    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long userId) throws UserNotFoundException;
}
