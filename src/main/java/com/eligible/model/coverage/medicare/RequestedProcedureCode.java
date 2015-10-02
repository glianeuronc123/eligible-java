package com.eligible.model.coverage.medicare;

import com.eligible.model.EligibleObject;
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
