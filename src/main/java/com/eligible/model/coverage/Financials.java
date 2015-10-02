package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Financials extends EligibleObject {
    Financial deductible;
    Financial stopLoss;
    FinancialFlowsPercents coinsurance;
    FinancialFlowsAmounts copayment;
    Financial costContainment;
    Financial spendDown;
    FinancialFlowsList limitations;
    List<String> disclaimer;
    FinancialFlowsList otherSources;
}
