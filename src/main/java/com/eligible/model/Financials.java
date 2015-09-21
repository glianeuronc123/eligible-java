package com.eligible.model;

import java.util.List;

/**
 * Created by ankitdimania on 18/09/15.
 */
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


    public Financial getDeductible() {
        return deductible;
    }

    public Financial getStopLoss() {
        return stopLoss;
    }

    public FinancialFlowsPercents getCoinsurance() {
        return coinsurance;
    }

    public FinancialFlowsAmounts getCopayment() {
        return copayment;
    }

    public Financial getCostContainment() {
        return costContainment;
    }

    public Financial getSpendDown() {
        return spendDown;
    }

    public FinancialFlowsList getLimitations() {
        return limitations;
    }

    public List<String> getDisclaimer() {
        return disclaimer;
    }

    public FinancialFlowsList getOtherSources() {
        return otherSources;
    }
}
