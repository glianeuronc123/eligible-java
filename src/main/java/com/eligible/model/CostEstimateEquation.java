package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
public class CostEstimateEquation extends EligibleObject {
    List<FinancialFlow> deductible;
    List<FinancialFlow> coinsurance;
    List<FinancialFlow> copayment;
    List<FinancialFlow> stopLoss;
}
