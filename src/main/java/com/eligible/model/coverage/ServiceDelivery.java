package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ServiceDelivery extends EligibleObject {
    Integer from;
    Integer to;
    String period;
    String type;
}
