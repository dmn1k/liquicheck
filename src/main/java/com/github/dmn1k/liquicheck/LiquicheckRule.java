package com.github.dmn1k.liquicheck;

import lombok.Data;


@Data
public abstract class LiquicheckRule<T> {
    private final Class<T> elementType;
    
    public abstract void onElementStart(ChangeLogElementValidation<T> validation);
    public abstract void onElementEnd(ChangeLogElementValidation<T> validation);
}
