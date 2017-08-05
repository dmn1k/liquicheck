package com.github.dmn1k.liquicheck;

import com.github.dmn1k.liquicheck.event.ChangeLogEnded;
import com.github.dmn1k.liquicheck.event.ChangeLogStarted;
import com.github.dmn1k.liquicheck.event.ChangeSetEnded;
import com.github.dmn1k.liquicheck.event.ChangeSetStarted;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import liquibase.Liquibase;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ResourceAccessor;

public class LiquiChecker {

    public void check(String changelogFile, ResourceAccessor resourceAccessor, List<LiquicheckRule> rules) {
        try {
            Liquibase liquibase = new Liquibase(changelogFile, resourceAccessor, (Database) null);
            DatabaseChangeLog databaseChangeLog = liquibase.getDatabaseChangeLog();

            rules.forEach(r -> r.on(new ChangeLogStarted(databaseChangeLog)));
            
            for (ChangeSet changeSet : databaseChangeLog.getChangeSets()) {
                rules.forEach(r -> r.on(new ChangeSetStarted(changeSet)));
                
                
                rules.forEach(r -> r.on(new ChangeSetEnded(changeSet)));
            }
            
            rules.forEach(r -> r.on(new ChangeLogEnded(databaseChangeLog)));
        } catch (LiquibaseException ex) {
            Logger.getLogger(LiquiChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
