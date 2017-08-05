package com.github.dmn1k.liquicheck;

import com.github.dmn1k.liquicheck.event.ChangeLogEnded;
import com.github.dmn1k.liquicheck.event.ChangeLogStarted;
import com.github.dmn1k.liquicheck.event.ChangeSetEnded;
import com.github.dmn1k.liquicheck.event.ChangeSetStarted;


public abstract class LiquicheckRule {
    public void on(ChangeLogStarted event){
        
    }
    
    public void on(ChangeLogEnded event){
        
    }
    
    public void on(ChangeSetStarted event){
        
    }
    
    public void on(ChangeSetEnded event){
        
    }
}
