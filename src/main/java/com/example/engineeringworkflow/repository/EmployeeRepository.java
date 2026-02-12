package com.example.engineeringworkflow.repository;

import com.example.engineeringworkflow.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
