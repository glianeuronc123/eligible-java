package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
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
}
