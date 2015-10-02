package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Financial extends EligibleObject {
    FinancialFlows remainings;
    FinancialFlows spent;
    FinancialFlows totals;
}
