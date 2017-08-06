package com.github.dmn1k.liquicheck;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import javax.enterprise.event.Event;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;
import lombok.SneakyThrows;

public class EventDispatcher {

    private static Field TYPE_LITERAL_TYPE_FIELD;

    static {
        for (Field field : TypeLiteral.class.getDeclaredFields()) {
            if (field.getType().equals(Type.class)) {
                field.setAccessible(true);
                TYPE_LITERAL_TYPE_FIELD = field;
                break;
            }
        }
    }

    @Inject
    @ElementStart
    private Event<ChangeLogElementValidation<?>> elementStartEvent;

    @Inject
    @ElementEnd
    private Event<ChangeLogElementValidation<?>> elementEndEvent;

    public void dispatchStart(ChangeLogElementValidation<?> event) {
        elementStartEvent.select(createTypeLiteral(event)).fire(event);
    }

    public void dispatchEnd(ChangeLogElementValidation<?> event) {
        elementEndEvent.select(createTypeLiteral(event)).fire(event);
    }

    @SneakyThrows
    private TypeLiteral<ChangeLogElementValidation<?>> createTypeLiteral(ChangeLogElementValidation<?> event) {
        TypeLiteral<ChangeLogElementValidation<?>> literal = new TypeLiteral<ChangeLogElementValidation<?>>() {
        };

        TYPE_LITERAL_TYPE_FIELD.set(literal, event.getType());
        return literal;
    }
}
