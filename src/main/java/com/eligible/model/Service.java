package com.eligible.model;

import java.util.List;
import java.util.Map;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class Service extends EligibleObject {
    String type;
    String typeLabel;
    String coverageStatus;
    String coverageStatusLabel;
    List<Map<String, Object>> coverageBasis;
    FinancialFlowsList facility;
    List<NonCovered> noncovered;
    BenefitDetails benefitDetails;
    Financials financials;
    Financial visits;
    List<AdditionalInsurancePolicy> additionalInsurancePolicies;


    public String getType() {
        return type;
    }

    public String getTypeLabel() {
        return typeLabel;
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

    public FinancialFlowsList getFacility() {
        return facility;
    }

    public List<NonCovered> getNoncovered() {
        return noncovered;
    }

    public BenefitDetails getBenefitDetails() {
        return benefitDetails;
    }

    public Financials getFinancials() {
        return financials;
    }

    public Financial getVisits() {
        return visits;
    }

    public List<AdditionalInsurancePolicy> getAdditionalInsurancePolicies() {
        return additionalInsurancePolicies;
    }
}
