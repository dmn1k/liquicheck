package com.github.dmn1k.liquicheck.event;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import java.lang.reflect.Type;
import lombok.Getter;

@Getter
public class ChangeLogElementValidation<T> {

    private final Type type;
    private final T payload;

    public ChangeLogElementValidation(T payload) {
        this.payload = payload;
        TypeToken<ChangeLogElementValidation<T>> typeToken = new TypeToken<ChangeLogElementValidation<T>>(){}
                .where(new TypeParameter<T>(){}, (Class<T>) payload.getClass());
        this.type = typeToken.getType();
    }
}
