package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper=false)
public class PreExistingCondition extends EligibleObject {
    List<Map<String, String>> waitingPeriod;
}
