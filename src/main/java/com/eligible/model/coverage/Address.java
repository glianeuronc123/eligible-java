package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public final class Address extends EligibleObject {
    String streetLine_1;
    String streetLine_2;
    String city;
    String state;
    String zip;
}
