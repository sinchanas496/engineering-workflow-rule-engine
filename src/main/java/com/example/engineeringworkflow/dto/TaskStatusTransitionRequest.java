package com.example.engineeringworkflow.dto;

import com.example.engineeringworkflow.model.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskStatusTransitionRequest {
    @NotNull
    private TaskStatus targetStatus;
    private String blockedReason;
}
