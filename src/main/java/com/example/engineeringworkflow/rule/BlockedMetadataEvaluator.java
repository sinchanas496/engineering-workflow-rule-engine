package com.example.engineeringworkflow.rule;

import com.example.engineeringworkflow.model.Rule;
import com.example.engineeringworkflow.model.RuleType;
import com.example.engineeringworkflow.model.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class BlockedMetadataEvaluator implements RuleEvaluator {
    @Override
    public RuleType supportedType() {
        return RuleType.BLOCKED_METADATA;
    }

    @Override
    public boolean evaluate(Rule rule, RuleEvaluationContext context) {
        if (context.getTargetStatus() != TaskStatus.BLOCKED) {
            return true;
        }
        return context.getBlockedReason() != null && !context.getBlockedReason().isBlank();
    }
}
