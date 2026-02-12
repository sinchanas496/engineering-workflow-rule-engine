package com.example.engineeringworkflow.service;

import com.example.engineeringworkflow.dto.TaskRequest;
import com.example.engineeringworkflow.dto.TaskResponse;
import com.example.engineeringworkflow.dto.TaskStatusTransitionRequest;

import java.util.List;

public interface TaskService {
    TaskResponse create(TaskRequest request);
    TaskResponse update(Long id, TaskRequest request);
    void delete(Long id);
    TaskResponse getById(Long id);
    List<TaskResponse> getAll();
    TaskResponse transitionStatus(Long taskId, TaskStatusTransitionRequest request);
}
