package com.example.engineeringworkflow.service.impl;

import com.example.engineeringworkflow.dto.DashboardResponse;
import com.example.engineeringworkflow.model.TaskStatus;
import com.example.engineeringworkflow.repository.TaskRepository;
import com.example.engineeringworkflow.repository.ViolationLogRepository;
import com.example.engineeringworkflow.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final TaskRepository taskRepository;
    private final ViolationLogRepository violationLogRepository;

    @Override
    public DashboardResponse getMetrics() {
        Map<String, Long> byStatus = new LinkedHashMap<>();
        for (TaskStatus status : TaskStatus.values()) {
            byStatus.put(status.name(), taskRepository.countByStatus(status));
        }

        return DashboardResponse.builder()
                .totalTasks(taskRepository.count())
                .totalViolations(violationLogRepository.count())
                .tasksByStatus(byStatus)
                .build();
    }
}
