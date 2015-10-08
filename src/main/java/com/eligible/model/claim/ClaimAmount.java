package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ClaimAmount extends EligibleObject {
    Double billed;
    Double paid;
    Double patientResponsibility;
    Double totalCoverage;
    Double promptPaymentDiscount;
    Double perDayLimit;
    Double patientPaid;
    Double revisedIntrest;
    Double negetiveLadgerBalance;
    Double tax;
    Double totalClaimBeforeTaxes;
    FederalAmount federal;

    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class FederalAmount extends EligibleObject {
        Double category_1;
        Double category_2;
        Double category_3;
        Double category_4;
        Double category_5;
    }
}
