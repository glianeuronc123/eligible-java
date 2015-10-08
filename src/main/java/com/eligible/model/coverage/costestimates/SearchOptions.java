package com.eligible.model.coverage.costestimates;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class SearchOptions extends EligibleObject {
    Boolean used;
    String parameters;
    Integer combinations;
}
