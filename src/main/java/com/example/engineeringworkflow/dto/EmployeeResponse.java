package com.example.engineeringworkflow.dto;

import com.example.engineeringworkflow.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {
    private Long id;
    private String fullName;
    private String email;
    private Role role;
}
