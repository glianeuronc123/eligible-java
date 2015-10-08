package com.eligible;

import com.eligible.model.Claim;

import java.io.FileNotFoundException;
import java.util.Collections;
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

    static void testCreateClaim() throws Exception {
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
