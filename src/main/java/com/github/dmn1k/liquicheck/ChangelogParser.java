package com.github.dmn1k.liquicheck;

import com.github.dmn1k.liquicheck.event.ChangeLogElementValidation;
import javax.inject.Inject;
import liquibase.change.Change;
import liquibase.changelog.DatabaseChangeLog;

public class ChangelogParser {
    @Inject
    private EventDispatcher eventDispatcher;
    
    public void parse(DatabaseChangeLog databaseChangeLog) {
        final Change change = databaseChangeLog.getChangeSets().get(0).getChanges().get(0);
        final ChangeLogElementValidation<Change> event = new ChangeLogElementValidation(change);
        eventDispatcher.dispatch(event);
    }
    
}
