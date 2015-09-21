package com.eligible.model;

/**
 */
public class Visit extends EligibleObject {
    Integer base;
    Integer used;
    Integer remaining;
    String type;
    String nextEligibleDate;


    public Integer getBase() {
        return base;
    }

    public Integer getUsed() {
        return used;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public String getType() {
        return type;
    }

    public String getNextEligibleDate() {
        return nextEligibleDate;
    }
}
