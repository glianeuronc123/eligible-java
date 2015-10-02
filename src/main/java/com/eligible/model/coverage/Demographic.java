package com.eligible.model.coverage;

import com.eligible.model.Address;
import com.eligible.model.Person;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Demographic extends Person {
    String memberId;
    String groupId;
    String groupName;
    String dob;
    String gender;
    String relationship;
    String relationshipCode;
    Address address;
}
