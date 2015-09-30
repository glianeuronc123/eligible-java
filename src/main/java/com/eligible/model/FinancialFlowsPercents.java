package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class FinancialFlowsPercents extends EligibleObject {
    FinancialFlows percents;
}
