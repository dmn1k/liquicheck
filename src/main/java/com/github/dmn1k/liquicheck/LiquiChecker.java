package com.github.dmn1k.liquicheck;

import liquibase.Liquibase;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.resource.ResourceAccessor;
import lombok.SneakyThrows;

public class LiquiChecker {

    @SneakyThrows
    public Violations check(String changelogFile, ResourceAccessor resourceAccessor, RuleSet ruleSet) {
        ChangelogValidator validator = new ChangelogValidator(ruleSet.getRules());
        Liquibase liquibase = new Liquibase(changelogFile, resourceAccessor, (Database) null);
        DatabaseChangeLog databaseChangeLog = liquibase.getDatabaseChangeLog();
        return validator.validate(databaseChangeLog);
    }
}
