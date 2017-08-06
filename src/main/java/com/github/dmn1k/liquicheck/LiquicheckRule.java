package com.github.dmn1k.liquicheck;

public interface LiquicheckRule<T> {
    void onElementStart(T element, Violations violations);
    void onElementEnd(T element, Violations violations);
}
