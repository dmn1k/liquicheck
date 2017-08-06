package com.github.dmn1k.liquicheck;

import lombok.Data;

@Data
public class Violation {
    private final ViolationLevel level;
    private final String message;
}
