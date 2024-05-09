package com.example.testtodoapp.service;

import com.example.testtodoapp.dto.auth.JwtResponse;
import com.example.testtodoapp.dto.auth.LoginDto;
import com.example.testtodoapp.dto.auth.RegisterDto;
import com.example.testtodoapp.dto.user.UserDto;
import com.example.testtodoapp.exceptions.UserExistException;

public interface AuthService {

    JwtResponse register(RegisterDto registerDto) throws UserExistException;

    JwtResponse login(LoginDto loginDto);
}
