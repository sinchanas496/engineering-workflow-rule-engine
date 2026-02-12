package com.example.engineeringworkflow.rule;

import com.example.engineeringworkflow.model.Rule;
import com.example.engineeringworkflow.model.RuleType;

public interface RuleEvaluator {
    RuleType supportedType();
    boolean evaluate(Rule rule, RuleEvaluationContext context);
}
