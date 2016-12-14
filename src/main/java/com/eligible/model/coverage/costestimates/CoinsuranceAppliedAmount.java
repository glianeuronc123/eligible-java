package com.eligible.model.coverage.costestimates;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class CoinsuranceAppliedAmount extends AppliedAmount {
    Double percent;
    Double appliedTo;
}
