package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Reserve extends EligibleObject {
    Double monetaryAmount;
    Integer totalDays;
    Integer remainingDays;
}
