package com.example.engineeringworkflow.service;

import com.example.engineeringworkflow.dto.RuleRequest;
import com.example.engineeringworkflow.dto.RuleResponse;

import java.util.List;

public interface RuleService {
    RuleResponse create(RuleRequest request);
    RuleResponse update(Long id, RuleRequest request);
    void delete(Long id);
    RuleResponse getById(Long id);
    List<RuleResponse> getAll();
}
