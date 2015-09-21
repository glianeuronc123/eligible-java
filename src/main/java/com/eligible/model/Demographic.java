package com.eligible.model;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class Demographic extends EligibleObject {
    String lastName;
    String firstName;
    String middleName;
    String memberId;
    String groupId;
    String groupName;
    String dob;
    String gender;
    String relationship;
    String relationshipCode;
    Address address;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getRelationship() {
        return relationship;
    }

    public String getRelationshipCode() {
        return relationshipCode;
    }

    public Address getAddress() {
        return address;
    }
}
