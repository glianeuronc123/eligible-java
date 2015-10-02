package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Physician extends EligibleObject {
    String eligibilityCode;
    String eligibilityCodeLabel;
    String insuranceType;
    String insuranceTypeLabel;
    Boolean primaryCare;
    Boolean restricted;
    List<ContactDetail> contactDetails;
    Dates dates;
    List<String> comments;
}
