package com.github.dmn1k.liquicheck;

import java.util.List;
import java.util.stream.Collectors;
import liquibase.change.Change;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;

public class ChangelogValidator {
    
    private final List<LiquicheckRule<?>> rules;

    public ChangelogValidator(List<LiquicheckRule<?>> rules) {
        this.rules = rules;
    }

    public ValidationResult validate(DatabaseChangeLog databaseChangeLog) {
        ValidationResult.ValidationResultBuilder resultBuilder = ValidationResult.builder();

        validate(databaseChangeLog, resultBuilder, () -> {
            for (ChangeSet changeSet : databaseChangeLog.getChangeSets()) {
                validate(changeSet, resultBuilder, () -> {
                    for (Change change : changeSet.getChanges()) {
                        validate(change, resultBuilder, () -> {
                        });
                    }
                });
            }
        });
        
        return resultBuilder.build();
    }

    private void validate(Object element, ValidationResult.ValidationResultBuilder resultBuilder, Runnable body) {
        ChangeLogElementValidation<?> validation = new ChangeLogElementValidation(element);
        List<LiquicheckRule> relevantRules = this.rules.stream()
                .filter(r -> r.getElementType().isAssignableFrom(element.getClass()))
                .collect(Collectors.toList());
        
        relevantRules.forEach(r -> r.onElementStart(validation));
        body.run();
        relevantRules.forEach(r -> r.onElementEnd(validation));
        resultBuilder.individualResults(validation.getValidationRuleResults());
    }
}
