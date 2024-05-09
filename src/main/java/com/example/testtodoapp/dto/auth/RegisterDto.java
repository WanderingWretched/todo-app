package com.example.testtodoapp.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class RegisterDto {
    @NotBlank(message = "Поле firstName обязательно для заполнения")
    String firstName;
    @NotBlank(message = "Поле lastName обязательно для заполнения")
    String lastName;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "Поле email неккоректно")
    @NotBlank(message = "Поле email обязательно для заполнения")
    String email;
    @NotBlank(message = "Поле password обязательно для заполнения")
    String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
