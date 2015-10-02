package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ClaimQuantity extends EligibleObject {
    Actual actual;
    Estimated estimated;
    Integer notReplacedBloodUnit;
    Integer outlierDays;
    Integer prescription;
    Integer visits;
    FederalQuantity federal;

    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class Actual extends EligibleObject {
        Integer covered;
        Integer coInsured;
        Integer lifeTimeReserve;
    }

    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class Estimated extends EligibleObject {
        Integer lifeTimeReserve;
        Integer nonCovered;
    }

    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class FederalQuantity extends EligibleObject {
        Integer category_1;
        Integer category_2;
        Integer category_3;
        Integer category_4;
        Integer category_5;
    }
}
