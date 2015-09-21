package com.eligible.model;

import java.util.List;

/**
 *
 */
public class RequestedServiceType extends EligibleObject {
    String type;
    String typeLabel;
    String planType;
    Boolean active;
    String startDate;
    String endDate;
    Double deductible;
    Double deductibleRemaining;
    Integer coinsurancePercent;
    Integer copayment;
    String infoValidTill;

    /* Hospital Snf Data */
    Boolean spellInProgress;
    String earliestClaim;
    String latestClaim;
    String contractor;
    CopaymentX2YDaysPerSpell copaymentUpTo20DaysPerSpell;
    CopaymentX2YDaysPerSpell copayment21Thru100DaysPerSpell;
    CopaymentX2YDaysPerSpell copayment61Thru90DaysPerSpell;
    Reserve lifetimeReserve;

    List<Visit> visits;
    List<MonetoryAmount> monetaryAmountUsed;
    String dialysisMethodStartDate;
    String kidneyTransplantHospitalDischargeDate;

    Boolean revoked;
    String revokedCode;
    String revokedLabel;
    String npi;

    Deductible bloodUnitsDeductible;

    /* Home health */
    String certificationDate;
    String recertificationDate;


    public String getType() {
        return type;
    }

    public String getTypeLabel() {
        return typeLabel;
    }

    public String getPlanType() {
        return planType;
    }

    public Boolean getActive() {
        return active;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
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

    public Boolean getSpellInProgress() {
        return spellInProgress;
    }

    public String getEarliestClaim() {
        return earliestClaim;
    }

    public String getLatestClaim() {
        return latestClaim;
    }

    public String getContractor() {
        return contractor;
    }

    public CopaymentX2YDaysPerSpell getCopaymentUpTo20DaysPerSpell() {
        return copaymentUpTo20DaysPerSpell;
    }

    public CopaymentX2YDaysPerSpell getCopayment21Thru100DaysPerSpell() {
        return copayment21Thru100DaysPerSpell;
    }

    public CopaymentX2YDaysPerSpell getCopayment61Thru90DaysPerSpell() {
        return copayment61Thru90DaysPerSpell;
    }

    public Reserve getLifetimeReserve() {
        return lifetimeReserve;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public List<MonetoryAmount> getMonetaryAmountUsed() {
        return monetaryAmountUsed;
    }

    public String getDialysisMethodStartDate() {
        return dialysisMethodStartDate;
    }

    public String getKidneyTransplantHospitalDischargeDate() {
        return kidneyTransplantHospitalDischargeDate;
    }

    public Boolean getRevoked() {
        return revoked;
    }

    public String getRevokedCode() {
        return revokedCode;
    }

    public String getRevokedLabel() {
        return revokedLabel;
    }

    public String getNpi() {
        return npi;
    }

    public Deductible getBloodUnitsDeductible() {
        return bloodUnitsDeductible;
    }

    public String getCertificationDate() {
        return certificationDate;
    }

    public String getRecertificationDate() {
        return recertificationDate;
    }
}
