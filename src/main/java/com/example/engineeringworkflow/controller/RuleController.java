package com.example.engineeringworkflow.controller;

import com.example.engineeringworkflow.dto.RuleRequest;
import com.example.engineeringworkflow.dto.RuleResponse;
import com.example.engineeringworkflow.service.RuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class RuleController {

    private final RuleService ruleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RuleResponse create(@Valid @RequestBody RuleRequest request) {
        return ruleService.create(request);
    }

    @PutMapping("/{id}")
    public RuleResponse update(@PathVariable Long id, @Valid @RequestBody RuleRequest request) {
        return ruleService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ruleService.delete(id);
    }

    @GetMapping("/{id}")
    public RuleResponse getById(@PathVariable Long id) {
        return ruleService.getById(id);
    }

    @GetMapping
    public List<RuleResponse> getAll() {
        return ruleService.getAll();
    }
}
