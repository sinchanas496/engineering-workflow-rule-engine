package com.example.engineeringworkflow.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ViolationLogResponse {
    private Long id;
    private Long taskId;
    private Long ruleId;
    private String message;
    private LocalDateTime timestamp;
}
