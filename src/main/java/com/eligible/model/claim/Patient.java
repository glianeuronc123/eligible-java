package com.eligible.model.claim;

import com.eligible.model.Person;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Patient extends Person {
    String id;
}
