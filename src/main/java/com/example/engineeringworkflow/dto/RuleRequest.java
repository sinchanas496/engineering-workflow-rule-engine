package com.example.engineeringworkflow.dto;

import com.example.engineeringworkflow.model.RuleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RuleRequest {
    @NotBlank
    private String name;

    @NotNull
    private RuleType type;

    @NotNull
    private Boolean active;

    @NotBlank
    private String errorMessage;

    private String sourceStatus;
    private String targetStatus;
    private String priorityCondition;
    private Integer minApprovals;

    @NotNull
    private Integer executionOrder;
}
