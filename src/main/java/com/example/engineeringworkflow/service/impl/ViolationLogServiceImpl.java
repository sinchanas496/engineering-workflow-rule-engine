package com.example.engineeringworkflow.service.impl;

import com.example.engineeringworkflow.dto.ViolationLogResponse;
import com.example.engineeringworkflow.repository.ViolationLogRepository;
import com.example.engineeringworkflow.service.ViolationLogService;
import com.example.engineeringworkflow.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViolationLogServiceImpl implements ViolationLogService {

    private final ViolationLogRepository violationLogRepository;

    @Override
    public List<ViolationLogResponse> getAll() {
        return violationLogRepository.findAll().stream().map(MapperUtil::toViolationResponse).toList();
    }

    @Override
    public List<ViolationLogResponse> getByTaskId(Long taskId) {
        return violationLogRepository.findByTaskIdOrderByTimestampDesc(taskId)
                .stream()
                .map(MapperUtil::toViolationResponse)
                .toList();
    }
}
