package com.example.engineeringworkflow.service;

import com.example.engineeringworkflow.dto.ViolationLogResponse;

import java.util.List;

public interface ViolationLogService {
    List<ViolationLogResponse> getAll();
    List<ViolationLogResponse> getByTaskId(Long taskId);
}
