package com.eligible.model.coverage.medicare;

import com.eligible.model.EligibleObject;
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
