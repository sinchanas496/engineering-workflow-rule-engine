package com.example.engineeringworkflow.rule;

import com.example.engineeringworkflow.model.Rule;
import com.example.engineeringworkflow.model.RuleType;
import com.example.engineeringworkflow.model.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class StatusPrerequisiteEvaluator implements RuleEvaluator {
    @Override
    public RuleType supportedType() {
        return RuleType.STATUS_PREREQUISITE;
    }

    @Override
    public boolean evaluate(Rule rule, RuleEvaluationContext context) {
        TaskStatus target = context.getTargetStatus();
        if (rule.getTargetStatus() == null || !rule.getTargetStatus().equals(target.name())) {
            return true;
        }
        return rule.getSourceStatus() != null && rule.getSourceStatus().equals(context.getTask().getStatus().name());
    }
}
