package com.github.dmn1k.liquicheck;

import liquibase.change.core.CreateTableChange;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Test;

public class LiquiCheckerTest {
    @Test
    public void testMethod() {
        LiquiChecker liquiChecker = new LiquiChecker();
        liquiChecker.check("changelogs/test.xml", new ClassLoaderResourceAccessor(), RuleSet.of(new TestRule()));
    }
    
    class TestRule implements LiquicheckRule<CreateTableChange> {

        @Override
        public Violations onElementStart(CreateTableChange element) {
            System.out.println("EVENT CAPTURED start");
            return Violations.empty();
        }

        @Override
        public Violations onElementEnd(CreateTableChange element) {
            System.out.println("EVENT CAPTURED end");
            return Violations.empty();
        }
        
    }
}
