package com.example.engineeringworkflow.rule;

import com.example.engineeringworkflow.model.Task;
import com.example.engineeringworkflow.model.TaskStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RuleEvaluationContext {
    private Task task;
    private TaskStatus targetStatus;
    private String blockedReason;
}
