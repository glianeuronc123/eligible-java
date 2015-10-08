package com.eligible.model.coverage.medicare;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Reserve extends EligibleObject {
    Double monetaryAmount;
    Integer totalDays;
    Integer remainingDays;
}
