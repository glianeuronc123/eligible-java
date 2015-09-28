package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Demographics extends EligibleObject {
    Demographic subscriber;
    Demographic dependent;
}
