package com.example.engineeringworkflow.service.impl;

import com.example.engineeringworkflow.dto.RuleRequest;
import com.example.engineeringworkflow.dto.RuleResponse;
import com.example.engineeringworkflow.exception.ResourceNotFoundException;
import com.example.engineeringworkflow.model.Rule;
import com.example.engineeringworkflow.repository.RuleRepository;
import com.example.engineeringworkflow.service.RuleService;
import com.example.engineeringworkflow.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;

    @Override
    public RuleResponse create(RuleRequest request) {
        Rule rule = buildRule(new Rule(), request);
        return MapperUtil.toRuleResponse(ruleRepository.save(rule));
    }

    @Override
    public RuleResponse update(Long id, RuleRequest request) {
        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));
        rule = buildRule(rule, request);
        return MapperUtil.toRuleResponse(ruleRepository.save(rule));
    }

    @Override
    public void delete(Long id) {
        if (!ruleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rule not found with id: " + id);
        }
        ruleRepository.deleteById(id);
    }

    @Override
    public RuleResponse getById(Long id) {
        return ruleRepository.findById(id)
                .map(MapperUtil::toRuleResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));
    }

    @Override
    public List<RuleResponse> getAll() {
        return ruleRepository.findAll().stream().map(MapperUtil::toRuleResponse).toList();
    }

    private Rule buildRule(Rule rule, RuleRequest request) {
        rule.setName(request.getName());
        rule.setType(request.getType());
        rule.setActive(request.getActive());
        rule.setErrorMessage(request.getErrorMessage());
        rule.setSourceStatus(request.getSourceStatus());
        rule.setTargetStatus(request.getTargetStatus());
        rule.setPriorityCondition(request.getPriorityCondition());
        rule.setMinApprovals(request.getMinApprovals());
        rule.setExecutionOrder(request.getExecutionOrder());
        return rule;
    }
}
