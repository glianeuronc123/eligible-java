package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class CostEstimate extends EligibleObject {
    Double providerPrice;
    CostEstimateEquation costEstimateEquation;
    CostEstimateEquation costEstimateAlternatives;
    Double costEstimate;
}
