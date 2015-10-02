package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class BenefitDetails extends EligibleObject {
    FinancialFlowsList benefitDescription;
    FinancialFlowsList managedCare;
    FinancialFlowsList unlimited;
}
