package com.eligible.model;

import com.eligible.net.APIResource;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ankitdimania on 16/09/15.
 */
public class Endpoint extends APIResource {
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

        String value;

        EndpointType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

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


    public String getStatusUpdatedAt() {
        return statusUpdatedAt;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Double getPassThroughFee() {
        return passThroughFee;
    }

    public Boolean getEnrollmentRequired() {
        return enrollmentRequired;
    }

    public String getAverageEnrollmentProcessTime() {
        return averageEnrollmentProcessTime;
    }

    public List<String> getEnrollmentMandatoryFields() {
        return enrollmentMandatoryFields;
    }

    public Boolean getSignatureRequired() {
        return signatureRequired;
    }

    public Boolean getBlueInkRequired() {
        return blueInkRequired;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDetails() {
        return statusDetails;
    }

    public Boolean getOriginalSignaturePdf() {
        return originalSignaturePdf == null ? Boolean.FALSE : originalSignaturePdf;
    }

}
