package com.example.engineeringworkflow.dto;

import com.example.engineeringworkflow.model.Priority;
import com.example.engineeringworkflow.model.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private Integer approvalsCount;
    private String blockedReason;
    private LocalDateTime blockedAt;
    private Long assignedEmployeeId;
    private String assignedEmployeeName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
