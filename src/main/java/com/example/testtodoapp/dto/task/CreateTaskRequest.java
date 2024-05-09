package com.example.testtodoapp.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTaskRequest {

    private Long userId;
    @NotBlank(message = "Поле title обязательно для заполнения")
    private String title;
    @NotBlank(message = "Поле description обязательно для заполнения")
    private String description;
    @NotNull(message = "Поле status обязательно для заполнения")
    private Long status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
