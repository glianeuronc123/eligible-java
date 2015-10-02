package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ServiceInformation extends EligibleObject {
    String procedureQualifier;
    String procedureCode;
    List<String> procedureModifiers;
    String revenueCode;
    String serviceStart;
    String serviceEnd;
    Amount amount;
    Quantity quantity;
    List<ReferenceInformation> additionalIds;
    List<ReferenceInformation> renderingProviderIds;
    Double allowedAmount;
    List<ReferenceInformation> remarkCodes;
    String providerControlNumber;
    List<String> healthcarePolicy;
    List<ClaimAdjustment> adjustments;

    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class Amount extends EligibleObject {
        Double billed;
        Double paid;
        Double totalCoverage;
        Double deduction;
        Double tax;
        Double totalClaimBeforeTaxes;
        Federal federal;


        @Getter
        @EqualsAndHashCode(callSuper = false)
        public static class Federal extends EligibleObject {
            Double category_1;
            Double category_2;
            Double category_3;
            Double category_4;
            Double category_5;
        }
    }

    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class Quantity extends EligibleObject {
        Double billed;
        Double paid;
        Federal federal;


        @Getter
        @EqualsAndHashCode(callSuper = false)
        public static class Federal extends EligibleObject {
            Integer category_1;
            Integer category_2;
            Integer category_3;
            Integer category_4;
            Integer category_5;
        }
    }
}
