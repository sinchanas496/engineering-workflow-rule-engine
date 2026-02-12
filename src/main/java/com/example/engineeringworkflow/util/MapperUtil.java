package com.example.engineeringworkflow.util;

import com.example.engineeringworkflow.dto.*;
import com.example.engineeringworkflow.model.*;

public final class MapperUtil {

    private MapperUtil() {
    }

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .role(employee.getRole())
                .build();
    }

    public static TaskResponse toTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .approvalsCount(task.getApprovalsCount())
                .blockedReason(task.getBlockedReason())
                .blockedAt(task.getBlockedAt())
                .assignedEmployeeId(task.getAssignedEmployee() != null ? task.getAssignedEmployee().getId() : null)
                .assignedEmployeeName(task.getAssignedEmployee() != null ? task.getAssignedEmployee().getFullName() : null)
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    public static RuleResponse toRuleResponse(Rule rule) {
        return RuleResponse.builder()
                .id(rule.getId())
                .name(rule.getName())
                .type(rule.getType())
                .active(rule.getActive())
                .errorMessage(rule.getErrorMessage())
                .sourceStatus(rule.getSourceStatus())
                .targetStatus(rule.getTargetStatus())
                .priorityCondition(rule.getPriorityCondition())
                .minApprovals(rule.getMinApprovals())
                .executionOrder(rule.getExecutionOrder())
                .build();
    }

    public static ViolationLogResponse toViolationResponse(ViolationLog log) {
        return ViolationLogResponse.builder()
                .id(log.getId())
                .taskId(log.getTaskId())
                .ruleId(log.getRuleId())
                .message(log.getMessage())
                .timestamp(log.getTimestamp())
                .build();
    }
}
