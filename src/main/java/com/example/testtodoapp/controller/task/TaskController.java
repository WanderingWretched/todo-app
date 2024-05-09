package com.example.testtodoapp.controller.task;

import com.example.testtodoapp.dto.task.CreateTaskRequest;
import com.example.testtodoapp.dto.task.TaskDto;
import com.example.testtodoapp.dto.task.UpdateTaskDto;
import com.example.testtodoapp.entity.task.Task;
import com.example.testtodoapp.exceptions.UserNotFoundException;
import com.example.testtodoapp.service.task.TaskService;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@Validated
public class TaskController {
    private final TaskService taskService;

    @Autowired
    TaskController(TaskService taskService) {
        this.taskService = taskService;

    }

    @PostMapping("/create")
    public TaskDto createTask(@Valid @RequestBody CreateTaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @GetMapping("/{userId}")
    public List<Task> getTasksByUserId(@Valid @PathVariable("userId") Long userId) {
        return taskService.getAllByUserId(userId);
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/task/{taskId}")
    public Task getTaskById(@PathVariable("taskId") Long taskId) {
        return taskService.getById(taskId);
    }

    @PatchMapping("/update-task/{taskId}")
    public Task updateTask(@PathVariable("taskId") Long taskId, @RequestBody UpdateTaskDto task) {
        return taskService.update(taskId, task);
    }

    @PutMapping("/update-task-status/{taskId}")
    public Task updateTaskStatus(@PathVariable("taskId") Long taskId, @RequestBody Long taskStatusId) {
        return taskService.updateTaskStatus(taskId, taskStatusId);
    }


    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
    }
}
