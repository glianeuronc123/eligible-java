package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class CopaymentX2YDaysPerSpell extends EligibleObject {
    Double monetaryAmount;
    Integer remainingDays;
}
