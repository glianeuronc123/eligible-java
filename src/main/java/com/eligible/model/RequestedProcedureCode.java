package com.eligible.model;

/**
 */
public class RequestedProcedureCode extends EligibleObject {
    String procedureCode;
    String procedureLabel;
    String planType;
    Boolean active;
    Double deductible;
    Double deductibleRemaining;
    Integer coinsurancePercent;
    Integer copayment;
    String infoValidTill;
    NextEligibleDate nextEligibleDate;

    public String getProcedureCode() {
        return procedureCode;
    }

    public String getProcedureLabel() {
        return procedureLabel;
    }

    public String getPlanType() {
        return planType;
    }

    public Boolean getActive() {
        return active;
    }

    public Double getDeductible() {
        return deductible;
    }

    public Double getDeductibleRemaining() {
        return deductibleRemaining;
    }

    public Integer getCoinsurancePercent() {
        return coinsurancePercent;
    }

    public Integer getCopayment() {
        return copayment;
    }

    public String getInfoValidTill() {
        return infoValidTill;
    }

    public NextEligibleDate getNextEligibleDate() {
        return nextEligibleDate;
    }
}
