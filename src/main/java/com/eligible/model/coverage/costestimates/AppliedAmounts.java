package com.eligible.model.coverage.costestimates;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=true)
public class AppliedAmounts extends AppliedAmountDetail {
    List<AppliedAmountDetail> details;
}
