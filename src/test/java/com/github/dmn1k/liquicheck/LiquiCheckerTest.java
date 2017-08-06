package com.github.dmn1k.liquicheck;

import javax.enterprise.event.Observes;
import liquibase.change.core.CreateTableChange;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Test;

public class LiquiCheckerTest {
    @Test
    public void testMethod() {
        LiquiChecker liquiChecker = new LiquiChecker();
        liquiChecker.check("changelogs/test.xml", new ClassLoaderResourceAccessor(), LiquiCheckerTest.class);
    }
    
    public void observes(@Observes @ElementStart ChangeLogElementValidation<CreateTableChange> event){
        System.out.println("EVENT CAPTURED");
    }
}
