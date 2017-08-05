package com.github.dmn1k.liquicheck.event;

import liquibase.changelog.DatabaseChangeLog;
import lombok.Data;

@Data
public class ChangeLogStarted {
    private final DatabaseChangeLog changelog;
}
