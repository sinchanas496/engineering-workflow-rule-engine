package com.example.engineeringworkflow.dto;

import com.example.engineeringworkflow.model.Priority;
import com.example.engineeringworkflow.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequest {
    @NotBlank
    private String title;
    private String description;

    @NotNull
    private Priority priority;

    @NotNull
    private Long assignedEmployeeId;

    private Integer approvalsCount;
    private TaskStatus status;
    private String blockedReason;
}
