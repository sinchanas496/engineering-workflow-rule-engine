package com.example.engineeringworkflow.service.impl;

import com.example.engineeringworkflow.dto.EmployeeRequest;
import com.example.engineeringworkflow.dto.EmployeeResponse;
import com.example.engineeringworkflow.exception.ResourceNotFoundException;
import com.example.engineeringworkflow.model.Employee;
import com.example.engineeringworkflow.repository.EmployeeRepository;
import com.example.engineeringworkflow.service.EmployeeService;
import com.example.engineeringworkflow.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = Employee.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        return MapperUtil.toEmployeeResponse(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setRole(request.getRole());
        return MapperUtil.toEmployeeResponse(employeeRepository.save(employee));
    }

    @Override
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponse getById(Long id) {
        return employeeRepository.findById(id)
                .map(MapperUtil::toEmployeeResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll().stream().map(MapperUtil::toEmployeeResponse).toList();
    }
}
