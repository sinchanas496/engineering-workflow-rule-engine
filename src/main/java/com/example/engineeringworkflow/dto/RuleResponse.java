package com.example.engineeringworkflow.dto;

import com.example.engineeringworkflow.model.RuleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RuleResponse {
    private Long id;
    private String name;
    private RuleType type;
    private Boolean active;
    private String errorMessage;
    private String sourceStatus;
    private String targetStatus;
    private String priorityCondition;
    private Integer minApprovals;
    private Integer executionOrder;
}
