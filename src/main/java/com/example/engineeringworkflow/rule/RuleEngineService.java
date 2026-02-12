package com.example.engineeringworkflow.rule;

import com.example.engineeringworkflow.exception.RuleViolationException;
import com.example.engineeringworkflow.model.Rule;
import com.example.engineeringworkflow.model.Task;
import com.example.engineeringworkflow.model.TaskStatus;
import com.example.engineeringworkflow.model.ViolationLog;
import com.example.engineeringworkflow.repository.RuleRepository;
import com.example.engineeringworkflow.repository.ViolationLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RuleEngineService {

    private final RuleRepository ruleRepository;
    private final ViolationLogRepository violationLogRepository;
    private final List<RuleEvaluator> evaluators;

    public void validateTransition(Task task, TaskStatus targetStatus, String blockedReason) {
        RuleEvaluationContext context = RuleEvaluationContext.builder()
                .task(task)
                .targetStatus(targetStatus)
                .blockedReason(blockedReason)
                .build();

        Map<?, RuleEvaluator> evaluatorMap = evaluators.stream()
                .collect(Collectors.toMap(RuleEvaluator::supportedType, Function.identity()));

        for (Rule rule : ruleRepository.findByActiveTrueOrderByExecutionOrderAsc()) {
            RuleEvaluator evaluator = evaluatorMap.get(rule.getType());
            if (evaluator == null) {
                continue;
            }

            boolean passed = evaluator.evaluate(rule, context);
            if (!passed) {
                String message = String.format("Rule '%s' failed: %s", rule.getName(), rule.getErrorMessage());
                ViolationLog violationLog = ViolationLog.builder()
                        .taskId(task.getId())
                        .ruleId(rule.getId())
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .build();
                violationLogRepository.save(violationLog);

                log.warn("Workflow rule violation | taskId={} ruleId={} ruleType={} message={}",
                        task.getId(), rule.getId(), rule.getType(), message);

                throw new RuleViolationException(message);
            }
        }
    }
}
