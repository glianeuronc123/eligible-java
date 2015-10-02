package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ClaimAdjustment extends EligibleObject {
    String typeCode;
    String typeLabel;
    String reasonCode;
    String reasonLabel;
    Double amount;
    Double quantity;
}
