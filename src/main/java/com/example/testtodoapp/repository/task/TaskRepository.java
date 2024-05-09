package com.example.testtodoapp.repository.task;


import com.example.testtodoapp.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(Long userId);

    Optional<Task> findByUserId(Long userId);
}
