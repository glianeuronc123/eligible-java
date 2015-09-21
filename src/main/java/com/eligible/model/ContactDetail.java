package com.eligible.model;

import java.util.List;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class ContactDetail extends EligibleObject {
    String entityCode;
    String entityCodeLabel;
    String lastName;
    String firstName;
    String identificationType;
    String identificationCode;
    List<Contact> contacts;
    Address address;


    public String getEntityCode() {
        return entityCode;
    }

    public String getEntityCodeLabel() {
        return entityCodeLabel;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Address getAddress() {
        return address;
    }
}
