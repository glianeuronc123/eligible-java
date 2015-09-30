package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
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
}
