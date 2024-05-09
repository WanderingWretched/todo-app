package com.example.testtodoapp.dto.task;

import jakarta.validation.constraints.NotNull;

public class GetTaskStatusByIdRequest {
    @NotNull(message = "Поле не можеть быть пустым")
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
