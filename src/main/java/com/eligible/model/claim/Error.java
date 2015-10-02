package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Error extends EligibleObject {
    String code;
    String param;
    String message;
    String expectedValue;
}
