package com.github.dmn1k.liquicheck;

import java.util.ArrayList;
import java.util.List;

public class Violations {
    private final List<Violation> violations = new ArrayList<>();
    
    public void info(String message) {
        violations.add(new Violation(ViolationLevel.INFO, message));
    }
    
    public void warn(String message) {
        violations.add(new Violation(ViolationLevel.WARN, message));
    }
    
    public void error(String message) {
        violations.add(new Violation(ViolationLevel.ERROR, message));
    }

    public List<Violation> getViolations() {
        return violations;
    }
}
