package com.example.testtodoapp.service.impl.user;

import com.example.testtodoapp.dto.user.FindUserRequest;
import com.example.testtodoapp.dto.user.UserDto;
import com.example.testtodoapp.entity.task.Task;
import com.example.testtodoapp.entity.user.User;
import com.example.testtodoapp.exceptions.ResourceNotFoundException;
import com.example.testtodoapp.exceptions.UserNotFoundException;
import com.example.testtodoapp.repository.UserRepository;
import com.example.testtodoapp.repository.task.TaskRepository;
import com.example.testtodoapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<UserDto> geAllUsers() {
        List<User> existUsers = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();
        existUsers.forEach(user -> usersDto.add(modelMapper.map(user, UserDto.class)));
        return usersDto;
    }

    @Override
    @Transactional
    public UserDto getUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Пользователь с id: " + userId + " не найден"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public List<UserDto> getByFirstName(FindUserRequest findUserRequest) {
        List<User> findUsers = userRepository.findAllByFirstName(findUserRequest.getFirstName()).orElseThrow(() -> new UsernameNotFoundException("Пользователь с именем: " + findUserRequest.getFirstName() + " не найден"));
        List<UserDto> foundedUsers = new ArrayList<>();
        findUsers.forEach(user -> foundedUsers.add(modelMapper.map(user, UserDto.class)));
        return foundedUsers;
    }

    @Override
    @Transactional
    public UserDto update(Long userId, UserDto user) throws UserNotFoundException {
        User existUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id: " + userId + " не найден"));
        existUser.setFirstName(user.getFirstName());
        existUser.setLastName(user.getLastName());
        existUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    @Transactional
    public boolean isTaskOwner(Long userId, Long taskId) {
        Task assignTask = taskRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Задача с id " + userId + "пользователя не найдена"));
        return assignTask != null;
    }

    @Override
    @Transactional
    public void delete(Long id) throws UserNotFoundException {
        User existUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id " + id + " не найден"));
        if (existUser != null) {
            userRepository.deleteById(id);
        }
    }
}
