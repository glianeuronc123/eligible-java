package com.eligible.model.claim;

import com.eligible.model.Address;
import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Payer extends EligibleObject {
    String name;
    String id;
    Address address;
    List<Contact> contacts;
    EntityIdentifier crossoverPayer;
    EntityIdentifier correctedPriorityPayer;
}
