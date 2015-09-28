package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Financial extends EligibleObject {
    FinancialFlows remainings;
    FinancialFlows spent;
    FinancialFlows totals;
}
