package com.github.dmn1k.liquicheck;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import liquibase.Liquibase;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.resource.ResourceAccessor;
import lombok.SneakyThrows;

public class LiquiChecker {

    @SneakyThrows
    public ValidationResult check(String changelogFile, ResourceAccessor resourceAccessor, Class<?>... rules) {
        try (SeContainer cdiContainer = SeContainerInitializer.newInstance()
                .disableDiscovery()
                .addBeanClasses(ChangelogValidator.class, EventDispatcher.class)
                .addBeanClasses(rules)
                .initialize()) {
            ChangelogValidator validator = cdiContainer.select(ChangelogValidator.class).get();
            Liquibase liquibase = new Liquibase(changelogFile, resourceAccessor, (Database) null);
            DatabaseChangeLog databaseChangeLog = liquibase.getDatabaseChangeLog();
            return validator.validate(databaseChangeLog);
        }
    }
}
