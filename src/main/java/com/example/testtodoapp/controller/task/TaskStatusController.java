package com.example.testtodoapp.controller.task;

import com.example.testtodoapp.entity.task.Status;
import com.example.testtodoapp.service.task.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task-statuses")
public class TaskStatusController {
    private final TaskStatusService taskStatusService;

    @Autowired
    TaskStatusController(TaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    @GetMapping
    public List<Status> getTaskStatuses() {
        return taskStatusService.getTaskStatuses();
    }
}
