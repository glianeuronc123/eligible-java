package com.eligible.model;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class Financial extends EligibleObject {
    FinancialFlows remainings;
    FinancialFlows spent;
    FinancialFlows totals;


    public FinancialFlows getRemainings() {
        return remainings;
    }

    public FinancialFlows getSpent() {
        return spent;
    }

    public FinancialFlows getTotals() {
        return totals;
    }
}
