package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class MonetoryAmount extends EligibleObject {
    Integer amount;
    String startDate;
    String endDate;
}
