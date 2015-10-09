package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public final class Address extends EligibleObject {
    String streetLine_1;
    String streetLine_2;
    String city;
    String state;
    String zip;
}
