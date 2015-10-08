package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Person extends EligibleObject {
    String firstName;
    String lastName;
    String middleName;
}
