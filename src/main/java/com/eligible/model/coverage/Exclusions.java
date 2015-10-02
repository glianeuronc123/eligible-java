package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Exclusions extends EligibleObject {
    List<NonCovered> noncovered;
    PreExistingCondition preExistingCondition;
}
