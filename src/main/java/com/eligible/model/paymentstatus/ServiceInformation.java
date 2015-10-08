package com.eligible.model.paymentstatus;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ServiceInformation extends EligibleObject {
    String procedureCode;
    List<String> procedureModifiers;
    String procedureQualifierCode;
    String procedureQualifierLabel;
    Amount amount;
    String quantity;
    List<StatusCode> codes;
    String controlNumber;
    String startDate;
    String endDate;
    String effectiveDate;
}
