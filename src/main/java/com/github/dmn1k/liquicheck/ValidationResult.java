package com.github.dmn1k.liquicheck;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class ValidationResult {
    @Singular
    private final List<ValidationRuleResult> individualResults;
}
