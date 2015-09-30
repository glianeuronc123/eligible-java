package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
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
}
