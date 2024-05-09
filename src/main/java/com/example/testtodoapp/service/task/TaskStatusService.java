package com.example.testtodoapp.service.task;

import com.example.testtodoapp.entity.task.Status;

import java.util.List;

public interface TaskStatusService {
    List<Status> getTaskStatuses();
}
