package com.example.engineeringworkflow.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class DashboardResponse {
    private long totalTasks;
    private long totalViolations;
    private Map<String, Long> tasksByStatus;
}
