package com.example.engineeringworkflow.dto;

import com.example.engineeringworkflow.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeRequest {
    @NotBlank
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Role role;
}
