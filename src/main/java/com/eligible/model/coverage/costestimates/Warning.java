package com.eligible.model.coverage.costestimates;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Warning extends EligibleObject {
    String code;
    String message;
    String param;
    String expectedValue;
    List<String> path;
}
