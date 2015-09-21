package com.eligible.model;

import java.util.List;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class Physician {
    String eligibilityCode;
    String eligibilityCodeLabel;
    String insuranceType;
    String insuranceTypeLabel;
    Boolean primaryCare;
    Boolean restricted;
    List<ContactDetail> contactDetails;
    Dates dates;
    List<String> comments;


    public String getEligibilityCode() {
        return eligibilityCode;
    }

    public String getEligibilityCodeLabel() {
        return eligibilityCodeLabel;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public String getInsuranceTypeLabel() {
        return insuranceTypeLabel;
    }

    public Boolean getPrimaryCare() {
        return primaryCare;
    }

    public Boolean getRestricted() {
        return restricted;
    }

    public List<ContactDetail> getContactDetails() {
        return contactDetails;
    }

    public Dates getDates() {
        return dates;
    }

    public List<String> getComments() {
        return comments;
    }
}
