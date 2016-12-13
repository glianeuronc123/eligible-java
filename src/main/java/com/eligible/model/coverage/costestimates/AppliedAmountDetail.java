package com.eligible.model.coverage.costestimates;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class AppliedAmountDetail extends EligibleObject {
    AppliedAmount deductible;
    CoinsuranceAppliedAmount coinsurance;
    AppliedAmount copayment;
}
