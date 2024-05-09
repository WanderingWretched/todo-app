package com.example.testtodoapp.service.task;

import com.example.testtodoapp.dto.task.CreateTaskRequest;
import com.example.testtodoapp.dto.task.TaskDto;
import com.example.testtodoapp.dto.task.UpdateTaskDto;
import com.example.testtodoapp.entity.task.Task;
import com.example.testtodoapp.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.List;

public interface TaskService {
    Task getById(Long id);

    List<TaskDto> getAllTasks();

    List<Task> getAllByUserId(Long id);

    Task update(Long taskId, UpdateTaskDto task);

    Task updateTaskStatus(Long taskId, Long taskStatusId);

    TaskDto createTask(CreateTaskRequest task);

    void delete(Long id);
}
