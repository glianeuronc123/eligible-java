package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class AdditionalInsurancePolicy extends EligibleObject {
    String insuranceType;
    String insuranceTypeLabel;
    String coverageDescription;
    List<Reference> reference;
    String payerType;
    String payerTypeLabel;
    List<ContactDetail> contactDetails;
    Dates dates;
    List<String> comments;
    List<String> serviceTypeCodes;
}
