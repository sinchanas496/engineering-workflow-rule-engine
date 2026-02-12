package com.example.engineeringworkflow.service;

import com.example.engineeringworkflow.dto.EmployeeRequest;
import com.example.engineeringworkflow.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest request);
    EmployeeResponse update(Long id, EmployeeRequest request);
    void delete(Long id);
    EmployeeResponse getById(Long id);
    List<EmployeeResponse> getAll();
}
