package com.eligible.model.paymentstatus;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Status extends EligibleObject {
    Boolean paid;
    String adjudicationStatus;
    List<StatusCode> codes;
    String effectiveDate;
    String finalizedDate;
    String remittanceDate;
    Amount amount;
    String traceNumber;
}
