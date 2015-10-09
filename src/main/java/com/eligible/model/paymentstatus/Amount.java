package com.eligible.model.paymentstatus;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Amount extends EligibleObject {
    Double billed;
    Double paid;
}
