package com.eligible.model.paymentstatus;

import com.eligible.model.EligibleObject;
import com.eligible.model.claim.Contact;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Payer extends EligibleObject {
    String name;
    String id;
    List<Contact> contacts;
}
