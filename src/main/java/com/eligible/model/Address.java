package com.eligible.model;

public final class Address extends EligibleObject {
    protected String streetLine_1;
    protected String streetLine_2;
    protected String city;
    protected String state;
    protected String zip;

    public String getStreetLine_1() {
        return streetLine_1;
    }

    public String getStreetLine_2() {
        return streetLine_2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}
