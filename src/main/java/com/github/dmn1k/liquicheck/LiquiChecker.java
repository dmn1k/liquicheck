package com.github.dmn1k.liquicheck;

import java.util.Arrays;
import liquibase.Liquibase;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.resource.ResourceAccessor;
import lombok.SneakyThrows;

public class LiquiChecker {

    @SneakyThrows
    public ValidationResult check(String changelogFile, ResourceAccessor resourceAccessor, LiquicheckRule<?>... rules) {
        ChangelogValidator validator = new ChangelogValidator(Arrays.asList(rules));
        Liquibase liquibase = new Liquibase(changelogFile, resourceAccessor, (Database) null);
        DatabaseChangeLog databaseChangeLog = liquibase.getDatabaseChangeLog();
        return validator.validate(databaseChangeLog);
    }
}
