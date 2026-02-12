package com.example.engineeringworkflow.repository;

import com.example.engineeringworkflow.model.Task;
import com.example.engineeringworkflow.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    long countByStatus(TaskStatus status);
    List<Task> findByAssignedEmployeeId(Long assignedEmployeeId);
}
