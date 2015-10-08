package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Insurance extends EligibleObject {
    String name;
    String id;
    String payerType;
    String payerTypeLabel;
    List<Contact> contacts;
    ServiceProviders serviceProviders;
}
