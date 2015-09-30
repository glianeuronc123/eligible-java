package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Exclusions extends EligibleObject {
    List<NonCovered> noncovered;
    PreExistingCondition preExistingCondition;
}
