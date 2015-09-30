package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Reference extends EligibleObject {
    String referenceCode;
    String referenceLabel;
    String referenceNumber;
}
