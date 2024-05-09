package com.example.testtodoapp.dto.user;

import com.example.testtodoapp.dto.task.TaskDto;
import com.example.testtodoapp.entity.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UserDto {
    private Long id;
    @NotBlank(message = "Поле обязательно для заполнения")
    private String firstName;
    @NotBlank(message = "Поле обязательно для заполнения")
    private String lastName;
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "Поле email неккоректно")
    @NotBlank(message = "Поле обязательно для заполнения")
    private String email;
    private Role role;
    private List<TaskDto> tasks;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
