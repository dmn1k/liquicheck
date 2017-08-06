package com.github.dmn1k.liquicheck;

import liquibase.change.core.CreateTableChange;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Test;

public class LiquiCheckerTest {
    @Test
    public void testMethod() {
        LiquiChecker liquiChecker = new LiquiChecker();
        liquiChecker.check("changelogs/test.xml", new ClassLoaderResourceAccessor(), new TestRule());
    }
    
    class TestRule extends LiquicheckRule<CreateTableChange> {

        public TestRule() {
            super(CreateTableChange.class);
        }

        @Override
        public void onElementStart(ChangeLogElementValidation<CreateTableChange> validation) {
            System.out.println("EVENT CAPTURED start");
        }

        @Override
        public void onElementEnd(ChangeLogElementValidation<CreateTableChange> validation) {
            System.out.println("EVENT CAPTURED end");
        }
        
    }
}
