package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class NextEligibleDate extends EligibleObject {
    String professional;
    String technical;
}
