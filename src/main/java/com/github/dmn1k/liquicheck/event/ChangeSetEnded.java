package com.github.dmn1k.liquicheck.event;

import liquibase.changelog.ChangeSet;
import lombok.Data;

@Data
public class ChangeSetEnded {
    private final ChangeSet changeSet;
}
