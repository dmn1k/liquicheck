package com.github.dmn1k.liquicheck;

import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class RuleSet {
    @Singular
    private List<LiquicheckRule<?>> rules;
    
    public static RuleSet of(LiquicheckRule<?>... rules){
        return RuleSet.builder()
                .rules(Arrays.asList(rules))
                .build();
    }
}
