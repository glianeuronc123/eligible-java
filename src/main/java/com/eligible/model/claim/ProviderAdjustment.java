package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ProviderAdjustment extends EligibleObject {
    String reasonCode;
    String reasonLabel;
    String id;
    String amount;
}
