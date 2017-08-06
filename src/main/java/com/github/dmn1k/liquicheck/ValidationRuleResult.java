package com.github.dmn1k.liquicheck;

import lombok.Data;

@Data
public class ValidationRuleResult {
    private final ValidationResultLevel level;
    private final String message;
}
