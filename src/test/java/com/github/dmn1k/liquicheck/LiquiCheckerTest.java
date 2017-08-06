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
        public void onElementStart(CreateTableChange element, Violations violations) {
            System.out.println("EVENT CAPTURED start");
        }

        @Override
        public void onElementEnd(CreateTableChange element, Violations violations) {
            System.out.println("EVENT CAPTURED end");
        }
        
    }
}
