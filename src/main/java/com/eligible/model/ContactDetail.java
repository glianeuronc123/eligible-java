package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
public class ContactDetail extends EligibleObject {
    String entityCode;
    String entityCodeLabel;
    String lastName;
    String firstName;
    String identificationType;
    String identificationCode;
    List<Contact> contacts;
    Address address;
}
