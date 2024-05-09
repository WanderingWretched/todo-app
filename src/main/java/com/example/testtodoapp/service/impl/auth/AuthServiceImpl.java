package com.example.testtodoapp.service.impl.auth;

import com.example.testtodoapp.dto.auth.JwtResponse;
import com.example.testtodoapp.dto.auth.LoginDto;
import com.example.testtodoapp.dto.auth.RegisterDto;
import com.example.testtodoapp.entity.user.Role;
import com.example.testtodoapp.entity.user.User;
import com.example.testtodoapp.exceptions.UserExistException;
import com.example.testtodoapp.repository.UserRepository;
import com.example.testtodoapp.security.JwtUtilities;
import com.example.testtodoapp.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;
    private final ModelMapper modelMapper;

    @Autowired
    AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtilities jwtUtilities,
            ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtilities = jwtUtilities;
        this.modelMapper = modelMapper;
    }

    @Override
    public JwtResponse register(RegisterDto registerDto) throws UserExistException {

        boolean userExist = userRepository.existsByEmail(registerDto.getEmail());

        if (userExist) {
            throw new UserExistException("Пользователь с таким email уже существует");
        } else {
            User user = new User();
            user.setEmail(registerDto.getEmail());
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setRole(Role.USER);
            userRepository.save(user);

            String token = jwtUtilities.generateToken(user);
            return new JwtResponse(token);
        }
    }

    @Override
    public JwtResponse login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким email не найден"));
        String token = jwtUtilities.generateToken(user);
        return new JwtResponse(token);
    }
}
