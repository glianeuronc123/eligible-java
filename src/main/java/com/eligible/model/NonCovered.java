package com.eligible.model;

import java.util.List;

/**
 * Created by ankitdimania on 22/09/15.
 */
// TODO check similarity with {@link FinancialFlow}
public class NonCovered extends EligibleObject {
    String type;
    String label;
    String timePeriod;
    String timePeriodLabel;
    String level;
    String network;
    String pos;
    String posLabel;
    Boolean authorizationRequired;
    List<ContactDetail> contactDetails;
    Dates dates;
    List<String> comments;


    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public String getTimePeriodLabel() {
        return timePeriodLabel;
    }

    public String getLevel() {
        return level;
    }

    public String getNetwork() {
        return network;
    }

    public String getPos() {
        return pos;
    }

    public String getPosLabel() {
        return posLabel;
    }

    public Boolean getAuthorizationRequired() {
        return authorizationRequired;
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
