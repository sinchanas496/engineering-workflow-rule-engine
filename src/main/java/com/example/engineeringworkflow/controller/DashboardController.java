package com.example.engineeringworkflow.controller;

import com.example.engineeringworkflow.dto.DashboardResponse;
import com.example.engineeringworkflow.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getMetrics() {
        return dashboardService.getMetrics();
    }
}
