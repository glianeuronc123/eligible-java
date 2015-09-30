package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Visit extends EligibleObject {
    Integer base;
    Integer used;
    Integer remaining;
    String type;
    String nextEligibleDate;
}
