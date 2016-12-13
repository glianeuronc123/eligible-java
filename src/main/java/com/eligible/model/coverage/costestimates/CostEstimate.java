package com.eligible.model.coverage.costestimates;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CostEstimate extends EligibleObject {
    Double providerPrice;
    CostEstimateEquation costEstimateEquation;
    CostEstimateEquation costEstimateAlternatives;
    Double costEstimate;
    String network;
}
