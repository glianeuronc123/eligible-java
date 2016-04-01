package com.eligible;

import com.eligible.model.EnrollmentNpi;
import com.eligible.model.enrollmentnpi.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EligibleEnrollmentNpisMain extends EligibleMainBase {
    static final Map<String, Object> queryParams = Collections.emptyMap();

    public static void main(String[] args) throws Exception {
//        testCreateEnrollmentNpi();
//        testRetrieveEnrollmentNpi();
//        testUpdateEnrollmentNpi();
//        testQueryEnrollmentNpi();

//        testRetrieveReceivedPdf();
//        testDownloadReceivedPdf();

//        testCreateOriginalSignaturePdf();
//        testRetrieveOriginalSignaturePdf();
//        testUpdateOriginalSignaturePdf();
//        testDownloadOriginalSignaturePdf();
        testDeleteOriginalSignaturePdf();

    }

    static Map<String, Object> createEnrollmentParams() throws Exception {
        Map<String, Object> coordinateParams = new HashMap<>();
        coordinateParams.put("lx", 47);
        coordinateParams.put("ly", 9);
        coordinateParams.put("mx", 47);
        coordinateParams.put("my", 9);

        List<Map> coordinates = new ArrayList<>();
        coordinates.add(coordinateParams);

        Map<String, Object> signatureParams = new HashMap<>();
        signatureParams.put("coordinates", coordinates);

        Map<String, Object> authorizedSignerParams = new HashMap<>();
        authorizedSignerParams.put("title", "title");
        authorizedSignerParams.put("first_name", "Lorem");
        authorizedSignerParams.put("last_name", "Ipsum");
        authorizedSignerParams.put("contact_number", "1478963250");
        authorizedSignerParams.put("email", "provider@eligibleapi.com");
        authorizedSignerParams.put("signature", signatureParams);

        Map<String, Object> enrollmentNpiParams = new HashMap<>();
        enrollmentNpiParams.put("payer_id", "ELIG_SNDBX");
        enrollmentNpiParams.put("payer_id", "0007");
        enrollmentNpiParams.put("endpoint", "coverage");
        enrollmentNpiParams.put("effective_date", "2012-12-24");
        enrollmentNpiParams.put("facility_name", "Quality");
        enrollmentNpiParams.put("provider_name", "Jane Austen");
        enrollmentNpiParams.put("tax_id", "12345678");
        enrollmentNpiParams.put("address", "125 Snow Shoe Road");
        enrollmentNpiParams.put("city", "Sacramento");
        enrollmentNpiParams.put("state", "CA");
        enrollmentNpiParams.put("zip", "94107");
        enrollmentNpiParams.put("ptan", "54321");
        enrollmentNpiParams.put("medicaid_id", "22222");
        enrollmentNpiParams.put("npi", String.valueOf(System.currentTimeMillis() / 1000));
        enrollmentNpiParams.put("authorized_signer", authorizedSignerParams);

        Map<String, Object> enrollmentParams = new HashMap<>();
        enrollmentParams.put("enrollment_npi", enrollmentNpiParams);

        return enrollmentParams;
    }

    static void testCreateEnrollment() throws Exception {
        EnrollmentNpi enrollment = EnrollmentNpi.create(createEnrollmentParams());
        System.out.println(enrollment);
    }

    static void testRetrieveEnrollmentNpi() throws Exception {
        EnrollmentNpiResponse enrollmentNpis = EnrollmentNpi.retrieve("557604291");
        System.out.println(enrollmentNpis);
    }

    static void testUpdateEnrollmentNpi() throws Exception {
        EnrollmentNpiResponse enrollmentNpis = EnrollmentNpi.update("557604291", createEnrollmentParams());
        System.out.println(enrollmentNpis);
    }

    static void testQueryEnrollmentNpi() throws Exception {
        EnrollmentNpiQueryResponse enrollmentNpisQueryResponse = readEnrollmentNpisListFile();
//        EnrollmentNpi enrollmentNpisQuery = EnrollmentNpi.query(queryParams);
        System.out.println(enrollmentNpisQueryResponse);
    }

    static void testRetrieveReceivedPdf() throws Exception {
        ReceivedPdfResponse receivedPdf = EnrollmentNpi.getReceivedPdf("557604291");
        System.out.println(receivedPdf);
    }

    static void testDownloadReceivedPdf() throws Exception {
        String receivedPdf = EnrollmentNpi.downloadReceivedPdf("557604291");
        System.out.println(receivedPdf);
    }

    static void testCreateOriginalSignaturePdf() throws Exception {
        String fileName = "./src/test/resources/com/eligible/EligibleSandboxEnrollmentPDF.pdf";
        OriginalSignaturePdfResponse originalSignaturePdf = EnrollmentNpi.createOriginalSignaturePdf("557604291", fileName);
        System.out.println(originalSignaturePdf);
    }

    static void testRetrieveOriginalSignaturePdf() throws Exception {
        OriginalSignaturePdfResponse originalSignaturePdf = EnrollmentNpi.getOriginalSignaturePdf("557604291");
        System.out.println(originalSignaturePdf);
    }

    static void testUpdateOriginalSignaturePdf() throws Exception {
        String fileName = "./src/test/resources/com/eligible/EligibleSandboxEnrollmentPDF.pdf";
        OriginalSignaturePdfResponse originalSignaturePdf = EnrollmentNpi.updateOriginalSignaturePdf("557604291", fileName);
        System.out.println(originalSignaturePdf);
    }

    static void testDownloadOriginalSignaturePdf() throws Exception {
        String originalSignaturePdf = EnrollmentNpi.downloadOriginalSignaturePdf("557604291");
        System.out.println(originalSignaturePdf);
    }

    static void testDeleteOriginalSignaturePdf() throws Exception {
        OriginalSignaturePdfDeleteResponse originalSignaturePdf = EnrollmentNpi.deleteOriginalSignaturePdf("557604291");
        System.out.println(originalSignaturePdf);
    }

    static EnrollmentNpiQueryResponse readEnrollmentNpisListFile() throws FileNotFoundException {
        String fileName = "./src/test/resources/com/eligible/model/enrollment_npis_list.json";
        return parseResource(fileName, EnrollmentNpiQueryResponse.class);
    }
}
