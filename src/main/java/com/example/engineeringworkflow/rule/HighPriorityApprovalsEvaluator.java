package com.example.engineeringworkflow.rule;

import com.example.engineeringworkflow.model.Priority;
import com.example.engineeringworkflow.model.Rule;
import com.example.engineeringworkflow.model.RuleType;
import org.springframework.stereotype.Component;

@Component
public class HighPriorityApprovalsEvaluator implements RuleEvaluator {
    @Override
    public RuleType supportedType() {
        return RuleType.HIGH_PRIORITY_APPROVALS;
    }

    @Override
    public boolean evaluate(Rule rule, RuleEvaluationContext context) {
        if (rule.getTargetStatus() != null && !rule.getTargetStatus().equals(context.getTargetStatus().name())) {
            return true;
        }
        if (rule.getPriorityCondition() != null && !rule.getPriorityCondition().equals(Priority.HIGH.name())) {
            return true;
        }
        if (context.getTask().getPriority() != Priority.HIGH) {
            return true;
        }
        int min = rule.getMinApprovals() == null ? 0 : rule.getMinApprovals();
        return context.getTask().getApprovalsCount() >= min;
    }
}
