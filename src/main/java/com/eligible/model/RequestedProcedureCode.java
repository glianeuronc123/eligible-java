package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
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
}
