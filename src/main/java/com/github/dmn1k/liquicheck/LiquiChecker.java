package com.github.dmn1k.liquicheck;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import liquibase.Liquibase;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ResourceAccessor;

public class LiquiChecker {

    public void check(String changelogFile, ResourceAccessor resourceAccessor, Class<?>... rules) {
        try (SeContainer cdiContainer = SeContainerInitializer.newInstance()
                .disableDiscovery()
                .addBeanClasses(ChangelogParser.class, EventDispatcher.class)
                .addBeanClasses(rules)
                .initialize()) {
            ChangelogParser parser = cdiContainer.select(ChangelogParser.class).get();
            Liquibase liquibase = new Liquibase(changelogFile, resourceAccessor, (Database) null);
            DatabaseChangeLog databaseChangeLog = liquibase.getDatabaseChangeLog();
            parser.parse(databaseChangeLog);
        } catch (LiquibaseException ex) {
            throw new RuntimeException(ex);
        }
    }
}
