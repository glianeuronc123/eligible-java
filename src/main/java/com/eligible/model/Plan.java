package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Plan extends EligibleObject {
    String type;
    String coverageStatus;
    String coverageStatusLabel;
    List<Map<String, Object>> coverageBasis;
    String planNumber;
    String planName;
    String planType;
    String planTypeLabel;
    String groupName;
    Dates dates;
    List<String> comments;
    Exclusions exclusions;
    Financials financials;
    BenefitDetails benefitDetails;
    List<AdditionalInsurancePolicy> additionalInsurancePolicies;
}
