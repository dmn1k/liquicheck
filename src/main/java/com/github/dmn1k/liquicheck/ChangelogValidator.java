package com.github.dmn1k.liquicheck;

import com.google.common.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
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

    public Violations validate(DatabaseChangeLog databaseChangeLog) {
        Violations violations = Violations.empty();

        validate(databaseChangeLog, violations, () -> {
            for (ChangeSet changeSet : databaseChangeLog.getChangeSets()) {
                validate(changeSet, violations, () -> {
                    for (Change change : changeSet.getChanges()) {
                        validate(change, violations, () -> {
                        });
                    }
                });
            }
        });
        
        return violations;
    }

    private void validate(Object element, Violations violations, Runnable body) {
        List<LiquicheckRule> relevantRules = this.rules.stream()
                .filter(r -> getGenericTypeArgument(r).isAssignableFrom(element.getClass()))
                .collect(Collectors.toList());
        
        relevantRules.forEach(r -> violations.merge(r.onElementStart(element)));
        body.run();
        relevantRules.forEach(r -> violations.merge(r.onElementEnd(element)));
    }
    
    private static Class<?> getGenericTypeArgument(LiquicheckRule<?> rule){
        List<Type> genericInterfaces = Arrays.asList(rule.getClass().getGenericInterfaces());
        return genericInterfaces.stream()
                .map(i -> (ParameterizedType) i)
                .filter(t -> ((Class<?>) t.getRawType()).equals(LiquicheckRule.class))
                .map(t -> (Class<?>) t.getActualTypeArguments()[0])
                .findFirst()
                .get();
    }
}
