package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Contact extends EligibleObject {
    String contactType;
    String contactValue;
}
