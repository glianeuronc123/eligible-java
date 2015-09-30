package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Deductible extends EligibleObject {
    String excluded;
    String remaining;
    String startDate;
    String endDate;
}
