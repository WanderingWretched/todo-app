package com.example.testtodoapp.controller;

import com.example.testtodoapp.dto.auth.JwtResponse;
import com.example.testtodoapp.dto.auth.LoginDto;
import com.example.testtodoapp.dto.auth.RegisterDto;
import com.example.testtodoapp.exceptions.UserExistException;
import com.example.testtodoapp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {
    private final AuthService authService;

    @Autowired
    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public JwtResponse register(@Valid @RequestBody RegisterDto registerDto) throws UserExistException {
        return authService.register(registerDto);
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
