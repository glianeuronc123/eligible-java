package com.eligible.model.payer;

import com.eligible.model.EligibleObject;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Endpoint extends EligibleObject {

    @Getter
    @RequiredArgsConstructor
    public enum EndpointType {
        @SerializedName("professional claims")
        PROFESSIONAL_CLAIMS("professional claims"),

        @SerializedName("institutional claims")
        INSTITUTIONAL_CLAIMS("institutional claims"),

        @SerializedName("dental claims")
        DENTAL_CLAIMS("dental claims"),

        @SerializedName("fetch and append")
        FETCH_AND_APPEND("fetch and append"),

        @SerializedName("cost estimate")
        COST_ESTIMATE("cost estimate"),

        @SerializedName("coverage")
        COVERAGE("coverage"),

        @SerializedName("payment reports")
        PAYMENT_REPORTS("payment reports"),

        @SerializedName("payment status")
        PAYMENT_STATUS("payment status"),

        @SerializedName("referral create")
        REFERRAL_CREATE("referral create"),

        @SerializedName("referral inquiry")
        REFERRAL_INQUIRY("referral inquiry"),

        @SerializedName("precertification create")
        PRECERTIFICATION_CREATE("precertification create"),

        @SerializedName("precertification inquiry")
        PRECERTIFICATION_INQUIRY("precertification inquiry"),

        ;

        private static Map<String, EndpointType> endpointTypeMap = new TreeMap<String, EndpointType>(String.CASE_INSENSITIVE_ORDER) {{
           for(EndpointType endpointType : EndpointType.values())
               put(endpointType.getValue(), endpointType);
        }};

        final String value;

        public static EndpointType fromValue(String value){
            EndpointType endpointType = endpointTypeMap.get(value);

            if(endpointType == null) {
                throw new RuntimeException("EndpointType for " + value + " not found");
            }
            return endpointType;
        }
    }

    String endpoint;
    Double passThroughFee;
    Boolean enrollmentRequired;
    String averageEnrollmentProcessTime;
    List<String> enrollmentMandatoryFields;
    Boolean signatureRequired;
    Boolean blueInkRequired;
    String message;
    String status;
    String statusDetails;
    String statusUpdatedAt;
    Boolean originalSignaturePdf;
}
