package com.github.dmn1k.liquicheck;

import com.github.dmn1k.liquicheck.event.ChangeLogElementValidation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import javax.enterprise.event.Event;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;

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
    private Event<ChangeLogElementValidation<?>> validationEvent;

    public void dispatch(ChangeLogElementValidation<?> event) {
        validationEvent.select(createTypeLiteral(event)).fire(event);
    }

    private TypeLiteral<ChangeLogElementValidation<?>> createTypeLiteral(ChangeLogElementValidation<?> event) {
        TypeLiteral<ChangeLogElementValidation<?>> literal = new TypeLiteral<ChangeLogElementValidation<?>>() {
        };
        
        try {
            TYPE_LITERAL_TYPE_FIELD.set(literal, event.getType());
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

        return literal;
    }
}
