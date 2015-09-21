package com.eligible.model;

import java.util.List;
import java.util.Map;

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


    public String getType() {
        return type;
    }

    public String getCoverageStatus() {
        return coverageStatus;
    }

    public String getCoverageStatusLabel() {
        return coverageStatusLabel;
    }

    public List<Map<String, Object>> getCoverageBasis() {
        return coverageBasis;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public String getPlanName() {
        return planName;
    }

    public String getPlanType() {
        return planType;
    }

    public String getPlanTypeLabel() {
        return planTypeLabel;
    }

    public String getGroupName() {
        return groupName;
    }

    public Dates getDates() {
        return dates;
    }

    public List<String> getComments() {
        return comments;
    }

    public Exclusions getExclusions() {
        return exclusions;
    }

    public Financials getFinancials() {
        return financials;
    }

    public BenefitDetails getBenefitDetails() {
        return benefitDetails;
    }

    public List<AdditionalInsurancePolicy> getAdditionalInsurancePolicies() {
        return additionalInsurancePolicies;
    }
}
