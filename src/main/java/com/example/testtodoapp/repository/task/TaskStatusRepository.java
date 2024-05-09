package com.example.testtodoapp.repository.task;

import com.example.testtodoapp.entity.task.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskStatusRepository extends JpaRepository<Status, Long> {
    List<Status> findAll();
}
