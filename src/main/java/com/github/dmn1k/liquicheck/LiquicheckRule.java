package com.github.dmn1k.liquicheck;

public interface LiquicheckRule<T> {
    Violations onElementStart(T element);
    Violations onElementEnd(T element);
}
