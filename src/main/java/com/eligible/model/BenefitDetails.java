package com.eligible.model;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class BenefitDetails extends EligibleObject {
    FinancialFlowsList benefitDescription;
    FinancialFlowsList managedCare;
    FinancialFlowsList unlimited;


    public FinancialFlowsList getBenefitDescription() {
        return benefitDescription;
    }

    public FinancialFlowsList getManagedCare() {
        return managedCare;
    }

    public FinancialFlowsList getUnlimited() {
        return unlimited;
    }
}
