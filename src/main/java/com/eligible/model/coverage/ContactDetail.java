package com.eligible.model.coverage;

import com.eligible.model.Address;
import com.eligible.model.Person;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ContactDetail extends Person {
    String entityCode;
    String entityCodeLabel;
    String identificationType;
    String identificationCode;
    List<Contact> contacts;
    Address address;
}
