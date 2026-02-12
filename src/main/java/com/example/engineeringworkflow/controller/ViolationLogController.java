package com.example.engineeringworkflow.controller;

import com.example.engineeringworkflow.dto.ViolationLogResponse;
import com.example.engineeringworkflow.service.ViolationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/violations")
@RequiredArgsConstructor
public class ViolationLogController {

    private final ViolationLogService violationLogService;

    @GetMapping
    public List<ViolationLogResponse> getAll(@RequestParam(required = false) Long taskId) {
        if (taskId != null) {
            return violationLogService.getByTaskId(taskId);
        }
        return violationLogService.getAll();
    }
}
