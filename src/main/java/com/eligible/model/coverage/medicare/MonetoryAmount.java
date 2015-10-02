package com.eligible.model.coverage.medicare;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class MonetoryAmount extends EligibleObject {
    Integer amount;
    String startDate;
    String endDate;
}
