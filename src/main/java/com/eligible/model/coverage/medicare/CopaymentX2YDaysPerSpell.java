package com.eligible.model.coverage.medicare;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CopaymentX2YDaysPerSpell extends EligibleObject {
    Double monetaryAmount;
    Integer remainingDays;
}
