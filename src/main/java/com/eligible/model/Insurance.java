package com.eligible.model;

import java.util.List;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class Insurance extends EligibleObject {
    String name;
    String id;
    String payerType;
    String payerTypeLabel;
    List<Contact> contacts;
    ServiceProviders serviceProviders;


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPayerType() {
        return payerType;
    }

    public String getPayerTypeLabel() {
        return payerTypeLabel;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public ServiceProviders getServiceProviders() {
        return serviceProviders;
    }
}
