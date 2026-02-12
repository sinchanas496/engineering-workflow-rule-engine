package com.example.engineeringworkflow.repository;

import com.example.engineeringworkflow.model.ViolationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViolationLogRepository extends JpaRepository<ViolationLog, Long> {
    List<ViolationLog> findByTaskIdOrderByTimestampDesc(Long taskId);
}
