package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper=false)
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
}
