package com.example.engineeringworkflow.service.impl;

import com.example.engineeringworkflow.dto.TaskRequest;
import com.example.engineeringworkflow.dto.TaskResponse;
import com.example.engineeringworkflow.dto.TaskStatusTransitionRequest;
import com.example.engineeringworkflow.exception.ResourceNotFoundException;
import com.example.engineeringworkflow.model.Employee;
import com.example.engineeringworkflow.model.Task;
import com.example.engineeringworkflow.model.TaskStatus;
import com.example.engineeringworkflow.repository.EmployeeRepository;
import com.example.engineeringworkflow.repository.TaskRepository;
import com.example.engineeringworkflow.rule.RuleEngineService;
import com.example.engineeringworkflow.service.TaskService;
import com.example.engineeringworkflow.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final RuleEngineService ruleEngineService;

    @Override
    public TaskResponse create(TaskRequest request) {
        Employee employee = employeeRepository.findById(request.getAssignedEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + request.getAssignedEmployeeId()));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(request.getStatus() != null ? request.getStatus() : TaskStatus.OPEN)
                .assignedEmployee(employee)
                .approvalsCount(request.getApprovalsCount() != null ? request.getApprovalsCount() : 0)
                .blockedReason(request.getBlockedReason())
                .blockedAt(request.getBlockedReason() != null ? LocalDateTime.now() : null)
                .build();

        return MapperUtil.toTaskResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponse update(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        Employee employee = employeeRepository.findById(request.getAssignedEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + request.getAssignedEmployeeId()));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setApprovalsCount(request.getApprovalsCount() != null ? request.getApprovalsCount() : 0);
        task.setAssignedEmployee(employee);

        if (request.getStatus() != null && request.getStatus() != task.getStatus()) {
            ruleEngineService.validateTransition(task, request.getStatus(), request.getBlockedReason());
            task.setStatus(request.getStatus());
        }

        if (request.getStatus() == TaskStatus.BLOCKED) {
            task.setBlockedReason(request.getBlockedReason());
            task.setBlockedAt(LocalDateTime.now());
        } else {
            task.setBlockedReason(null);
            task.setBlockedAt(null);
        }

        return MapperUtil.toTaskResponse(taskRepository.save(task));
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public TaskResponse getById(Long id) {
        return taskRepository.findById(id)
                .map(MapperUtil::toTaskResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @Override
    public List<TaskResponse> getAll() {
        return taskRepository.findAll().stream().map(MapperUtil::toTaskResponse).toList();
    }

    @Override
    public TaskResponse transitionStatus(Long taskId, TaskStatusTransitionRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        ruleEngineService.validateTransition(task, request.getTargetStatus(), request.getBlockedReason());
        task.setStatus(request.getTargetStatus());
        if (request.getTargetStatus() == TaskStatus.BLOCKED) {
            task.setBlockedReason(request.getBlockedReason());
            task.setBlockedAt(LocalDateTime.now());
        } else {
            task.setBlockedReason(null);
            task.setBlockedAt(null);
        }

        return MapperUtil.toTaskResponse(taskRepository.save(task));
    }
}
