package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RemittanceDeliverMethod extends EligibleObject {
    String code;
    String codeLabel;
    String name;
    String communicationNumber;
}
