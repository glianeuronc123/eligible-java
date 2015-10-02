package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Date extends EligibleObject {
    String dateType;
    String dateValue;
}
