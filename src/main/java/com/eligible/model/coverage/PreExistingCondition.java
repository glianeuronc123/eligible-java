package com.eligible.model.coverage;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper = false)
public class PreExistingCondition extends EligibleObject {
    List<Map<String, ?>> waitingPeriod;
}
