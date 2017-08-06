package com.github.dmn1k.liquicheck;

import javax.inject.Inject;
import liquibase.change.Change;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;

public class ChangelogValidator {

    @Inject
    private EventDispatcher eventDispatcher;

    public ValidationResult validate(DatabaseChangeLog databaseChangeLog) {
        ValidationResult.ValidationResultBuilder resultBuilder = ValidationResult.builder();

        validate(new ChangeLogElementValidation<>(databaseChangeLog), resultBuilder, () -> {
            for (ChangeSet changeSet : databaseChangeLog.getChangeSets()) {
                validate(new ChangeLogElementValidation<>(changeSet), resultBuilder, () -> {
                    for (Change change : changeSet.getChanges()) {
                        validate(new ChangeLogElementValidation<>(change), resultBuilder, () -> {
                        });
                    }
                });
            }
        });
        
        return resultBuilder.build();
    }

    private void validate(ChangeLogElementValidation<?> validationEvent, ValidationResult.ValidationResultBuilder resultBuilder, Runnable body) {
        eventDispatcher.dispatchStart(validationEvent);
        body.run();
        eventDispatcher.dispatchEnd(validationEvent);
        resultBuilder.individualResults(validationEvent.getValidationRuleResults());
    }
}
