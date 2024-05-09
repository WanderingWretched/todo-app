package com.example.testtodoapp.dto.user;

import jakarta.validation.constraints.NotBlank;

public class FindUserRequest {
    @NotBlank(message = "Поле обязательно для заполнения")
    private String firstName;


    public FindUserRequest() {

    }

    public FindUserRequest(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
