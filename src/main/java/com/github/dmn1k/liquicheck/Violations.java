package com.github.dmn1k.liquicheck;

import java.util.ArrayList;
import java.util.List;

public class Violations {

    private final List<Violation> violations = new ArrayList<>();

    private Violations() {
        // use factory methods
    }

    public static Violations empty() {
        return new Violations();
    }

    public Violations info(String message) {
        violations.add(new Violation(ViolationLevel.INFO, message));
        return this;
    }

    public Violations warn(String message) {
        violations.add(new Violation(ViolationLevel.WARN, message));
        return this;
    }

    public Violations error(String message) {
        violations.add(new Violation(ViolationLevel.ERROR, message));
        return this;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public Violations merge(Violations other) {
        this.violations.addAll(other.getViolations());
        return this;
    }
}
