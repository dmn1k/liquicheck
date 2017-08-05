package com.github.dmn1k.liquicheck.event;

import liquibase.changelog.ChangeSet;
import lombok.Data;

@Data
public class ChangeSetStarted {
    private final ChangeSet changeSet;
}
