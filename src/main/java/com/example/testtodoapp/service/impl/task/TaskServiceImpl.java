package com.example.testtodoapp.service.impl.task;

import com.example.testtodoapp.dto.task.CreateTaskRequest;
import com.example.testtodoapp.dto.task.TaskDto;
import com.example.testtodoapp.dto.task.UpdateTaskDto;
import com.example.testtodoapp.entity.task.Status;
import com.example.testtodoapp.entity.task.Task;
import com.example.testtodoapp.entity.user.User;
import com.example.testtodoapp.exceptions.ResourceNotFoundException;
import com.example.testtodoapp.repository.UserRepository;
import com.example.testtodoapp.repository.task.TaskRepository;
import com.example.testtodoapp.repository.task.TaskStatusRepository;
import com.example.testtodoapp.service.task.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Cacheable("TaskServiceImpl")
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    TaskServiceImpl(
            TaskRepository taskRepository,
            UserRepository userRepository,
            TaskStatusRepository taskStatusRepository,
            ModelMapper modelMapper
    ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    @Cacheable(value = "TaskServiceImpl::getById", key = "#id")
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto> tasksDto = new ArrayList<>();
        tasks.forEach(task -> tasksDto.add(modelMapper.map(task, TaskDto.class)));
        return tasksDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long userId) throws
            ResourceNotFoundException {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    @CachePut(
            value = "TaskServiceImpl::getById",
            key = "#task.id"
    )
    public Task update(Long taskId, UpdateTaskDto task) {
        Task existTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Задача с id " + taskId + " не найдена"));


        if (task.getTitle() != null) {
            existTask.setTitle(task.getTitle());
        }

        if (task.getDescription() != null) {
            existTask.setDescription(task.getDescription());
        }

        if (task.getStatus() != null) {
            Status taskStatus = taskStatusRepository
                    .findById(task.getStatus())
                    .orElse(null);
            existTask.setStatus(taskStatus);
        }

        if (task.getUserId() != null) {
            User assignUser = userRepository.findById(task.getUserId())
                    .orElse(null);
            existTask.setUser(assignUser);
        }

        return taskRepository.save(existTask);
    }

    @Override
    @Transactional
    public Task updateTaskStatus(Long taskId, Long taskStatusId) {
        Task existTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Задача с id " + taskId + " не найдена"));

        Status taskStatus = taskStatusRepository
                .findById(taskStatusId).orElseThrow(() -> new ResourceNotFoundException("Статус задачу с id" + taskStatusId + " не найден"));

        existTask.setStatus(taskStatus);
        return taskRepository.save(existTask);
    }

    @Override
    @Transactional
    @Cacheable(
            value = "TaskServiceImpl::getById",
            condition = "#task.id!=null",
            key = "#task.id"
    )
    public TaskDto createTask(CreateTaskRequest taskRequest) {
        User assignUser;

        if (taskRequest.getUserId() != null) {
            assignUser = userRepository.findById(taskRequest.getUserId()).orElse(null);
        } else {
            assignUser = null;
        }

        Status taskStatus = taskStatusRepository.findById(taskRequest.getStatus())
                .orElseThrow(() -> new ResourceNotFoundException("Статус задачи с id " + taskRequest.getStatus() + " не найден"));

        Task task = new Task();

        task.setStatus(taskStatus);
        task.setUser(assignUser);
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        Task savedTask = taskRepository.save(task);

        return modelMapper.map(savedTask, TaskDto.class);
    }


    @Override
    @Transactional
    @CacheEvict(
            value = "TaskServiceImpl::getById",
            key = "#id"
    )
    public void delete(Long id) {
        Task taskToDelete = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Задача с id " + id + " не найдена"));
        if (taskToDelete != null) {
            taskRepository.deleteById(id);
        }
    }
}
