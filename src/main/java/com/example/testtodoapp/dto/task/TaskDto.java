package com.example.testtodoapp.dto.task;

import com.example.testtodoapp.dto.user.UserDto;
import com.example.testtodoapp.entity.task.Status;

public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private Status status;
    UserDto user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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



    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
