package com.eligible;

import com.eligible.model.Claim;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EligibleClaimMain extends EligibleMainBase {
    static final Map<String, Object> queryParams = Collections.emptyMap();

    public static void main(String[] args) throws Exception {
//        testCreateClaim();

//        testClaimAck();
//        testQueryClaimAck();

//        testPaymentReport();
//        testPaymentReportWithPaymentReportId();
//        testQueryPaymentReport();

    }

    Map<String, Object> createAddress(String line1, String line2,
                                             String city, String state, String zip) {
        Map<String, Object> address = new HashMap<>();
        address.put("street_line_1", line1);
        address.put("street_line_2", line2);
        address.put("city", city);
        address.put("state", state);
        address.put("zip", zip);

        return address;
    }

    Map<String, Object> createBillingProvider() {
        Map<String, Object> billingProvider = new HashMap<>();
        billingProvider.put("tax_id", "123456789");
        billingProvider.put("tax_id_type", "EI");
        billingProvider.put("entity", "false");
        billingProvider.put("phone_number", "1234567890");
        billingProvider.put("organization_name", "ELIGIBLE INC");
        billingProvider.put("last_name", "SOME");
        billingProvider.put("first_name", "PROVIDER");
        billingProvider.put("middle_name", null);
        billingProvider.put("address", createAddress("1842 UNION STREET", null, "Seattle", "WA", "981011231"));
        billingProvider.put("npi", "1234567893");
        billingProvider.put("taxonomy_code", "101YM0800X");
        return billingProvider;
    }

    Map<String, Object> createPayer() {
        Map<String, Object> payer = new HashMap<>();
        payer.put("id", "60054");
        payer.put("name", "Aetna");
        payer.put("address", createAddress("603 3rd Ave Van", null, "San Francisco", "CA", "941231232"));
        return payer;
    }

    Map<String, Object> createSubsciber() {
        Map<String, Object> subscriber = new HashMap<>();
        subscriber.put("id", "1234567890");
        subscriber.put("first_name", "Benjamin");
        subscriber.put("last_name", "Franklin");
        subscriber.put("middle_name", null);
        subscriber.put("address", createAddress("123 NW St", null, "Seattle", "WA", "981171232"));
        subscriber.put("phone_number", "9129129121");
        subscriber.put("group_id", "100012345");
        subscriber.put("dob", "1974-02-06");
        subscriber.put("gender", "M");
        subscriber.put("group_name", null);
        return subscriber;
    }

    Map<String, Object> createDependent() {
        Map<String, Object> dependent = new HashMap<>();
        dependent.put("last_name", "Franklin");
        dependent.put("first_name", "Cheryl");
        dependent.put("middle_name", null);
        dependent.put("dob", "1976-03-06");
        dependent.put("gender", "F");
        dependent.put("address", createAddress("123 NW St", null, "Seattle", "WA", "981171232"));
        dependent.put("relationship", "01");
        dependent.put("phone_number", "9129129123");
        return dependent;
    }

    Map<String, Object> createRenderingProvider() {
        Map<String, Object> renderingProvider = new HashMap<>();
        renderingProvider.put("entity", null);
        renderingProvider.put("organization_name", null);
        renderingProvider.put("last_name", "Franklin");
        renderingProvider.put("first_name", "John");
        renderingProvider.put("npi", "1234567893");
        return renderingProvider;
    }

    Map<String, Object> createServiceLine() {
        Map<String, Object> serviceLine = new HashMap<>();
        serviceLine.put("service_date_from", "2014-05-07");
        serviceLine.put("service_date_to", "2014-05-07");
        serviceLine.put("place_of_service", "11");
        serviceLine.put("procedure_code", "90837");
        serviceLine.put("procedure_modifiers", Arrays.asList("UN"));
        serviceLine.put("diagnosis_code_pointers", Arrays.asList("1"));
        serviceLine.put("charge_amount", "118.05");
        serviceLine.put("units", "1");
        serviceLine.put("rendering_provider", createRenderingProvider());
        return serviceLine;
    }

    Map<String, Object> createClaim() {
        Map<String, Object> claim = new HashMap<>();
        claim.put("patient_signature_on_file", "Y");
        claim.put("direct_payment_authorized", "Y");
        claim.put("frequency", "1");
        claim.put("prior_authorization_number", "1234567890");
        claim.put("accept_assignment_code", "C");
        claim.put("total_charge", "118.05");
        claim.put("patient_amount_paid", "0");
        claim.put("provider_signature_on_file", "Y");
        claim.put("diagnosis_codes", Arrays.asList("309.24", "309.0"));
        claim.put("service_lines", Arrays.asList(createServiceLine()));
        return claim;
    }

    Map<String, Object> createClaimCreateParams() {
        Map<String, Object> claimParams = new HashMap<>();
        claimParams.put("scrub_eligibility", "false");
        claimParams.put("billing_provider", createBillingProvider());
        claimParams.put("payer", createPayer());
        claimParams.put("subscriber", createSubsciber());
        claimParams.put("dependent", createDependent());
        claimParams.put("claim", createClaim());
        return claimParams;
    }

    void testCreateClaim() throws Exception {
        String fileName = "./src/test/resources/com/eligible/claim_request.json";
        Map claimParams = parseResource(fileName, Map.class);

        Claim claim = Claim.create(claimParams);
        System.out.println(claim);
    }

    static void testClaimAck() throws Exception {
        Claim.Acknowledgements acknowledgements = Claim.getAcknowledgements("12121212");
        System.out.println(acknowledgements);
    }

    static void testQueryClaimAck() throws Exception {
        Claim.Acknowledgements acknowledgements = Claim.queryAcknowledgements(queryParams);
        System.out.println(acknowledgements);
    }

    static void testPaymentReport() throws Exception {
        Claim.PaymentReport paymentReport = Claim.getPaymentReport("BDA85HY09IJ");
        System.out.println(paymentReport);
    }

    static void testPaymentReportWithPaymentReportId() throws Exception {
        Claim.PaymentReport paymentReport = Claim.getPaymentReport("BDA85HY09IJ", "ABX45DGER44");
        System.out.println(paymentReport);
    }

    static void testQueryPaymentReport() throws Exception {
        Claim.PaymentReports paymentReports = readPaymentReportsFile();
//        Claim.PaymentReports paymentReports = Claim.queryPaymentReports(queryParams);
        System.out.println(paymentReports);
    }

    static Claim.PaymentReports readPaymentReportsFile() throws FileNotFoundException {
        String fileName = "./src/test/resources/com/eligible/model/payment_reports.json";
        return parseResource(fileName, Claim.PaymentReports.class);
    }
}
