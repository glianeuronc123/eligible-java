package com.eligible.model.coverage.medicare;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Deductible extends EligibleObject {
    String excluded;
    String remaining;
    String startDate;
    String endDate;
}
