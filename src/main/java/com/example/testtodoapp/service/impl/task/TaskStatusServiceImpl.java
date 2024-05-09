package com.example.testtodoapp.service.impl.task;

import com.example.testtodoapp.entity.task.Status;
import com.example.testtodoapp.exceptions.ResourceNotFoundException;
import com.example.testtodoapp.repository.task.TaskStatusRepository;
import com.example.testtodoapp.service.task.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;

    @Autowired
    TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    @Transactional
    public List<Status> getTaskStatuses() {
        return taskStatusRepository.findAll();
    }
}
