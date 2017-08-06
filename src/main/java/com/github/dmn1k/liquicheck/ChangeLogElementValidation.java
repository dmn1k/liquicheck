package com.github.dmn1k.liquicheck;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChangeLogElementValidation<T> {

    private final Type type;
    private final T element;
    private final List<ValidationRuleResult> validationRuleResults = new ArrayList<>();

    public ChangeLogElementValidation(T payload) {
        this.element = payload;
        TypeToken<ChangeLogElementValidation<T>> typeToken = new TypeToken<ChangeLogElementValidation<T>>(){}
                .where(new TypeParameter<T>(){}, (Class<T>) payload.getClass());
        this.type = typeToken.getType();
    }

    public void addValidationResult(ValidationRuleResult result){
        validationRuleResults.add(result);
    }
    
    public Type getType() {
        return type;
    }

    public T getElement() {
        return element;
    }

    public List<ValidationRuleResult> getValidationRuleResults() {
        return validationRuleResults;
    }
    
    
}
