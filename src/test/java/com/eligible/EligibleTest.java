package com.eligible;

import com.eligible.exception.APIErrorResponseException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.EligibleException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.model.Claim;
import com.eligible.model.Coverage;
import com.eligible.model.EnrollmentNpi;
import com.eligible.model.Payer;
import com.eligible.model.PaymentStatus;
import com.eligible.model.enrollmentnpi.AuthorizedSigner;
import com.eligible.model.enrollmentnpi.EnrollmentNpiQueryResponse;
import com.eligible.model.enrollmentnpi.EnrollmentNpiResponse;
import com.eligible.model.enrollmentnpi.OriginalSignaturePdfDeleteResponse;
import com.eligible.model.enrollmentnpi.OriginalSignaturePdfResponse;
import com.eligible.model.enrollmentnpi.ReceivedPdfResponse;
import com.eligible.net.APIResource;
import com.eligible.net.RequestOptions;
import com.eligible.util.StringUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.eligible.util.TestUtil.resource;
import static java.lang.Boolean.parseBoolean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Main API Tests for running real Sandbox API calls.
 */
public class EligibleTest {
    static boolean extensiveTesting;
    static Map<String, Object> defaultCoverageParams = new HashMap<String, Object>();
    static Map<String, Object> invalidCoverageParams = new HashMap<String, Object>();
    static Map<String, Object> emptyCoverageParams = new HashMap<>();
    static Map<String, Object> defaultCoverageMedicareParams = new HashMap<String, Object>();
    static Map<String, Object> defaultCoverageCostEstimateParams = new HashMap<String, Object>();
    static Map<String, Object> defaultClaimParams = new HashMap<String, Object>();
    static String defaultClaimAckReferenceId = "8IZ9JZI2FUEDCS";
    static Map<String, Object> defaultClaimAckParams = new HashMap<String, Object>();
    static String defaultClaimPaymentReportClaimReferenceId = "8IZ9JZI2FUEDCS";
    static String defaultClaimPaymentReportId = "UP4OCS4PUY455";
    static Map<String, Object> defaultPaymentReportsParams = new HashMap<String, Object>();
    static Map<String, Object> defaultPaymentStatusParams = new HashMap<>();
    static Map<String, Object> enrollmentNpisQueryParams = new HashMap<>();

    @Before
    public void before() {
    }

    @BeforeClass
    public static void setUp() throws Exception {
        String apiKey = System.getenv("API_KEY");
        extensiveTesting = parseBoolean(System.getenv("API_KEY"));
        if (StringUtil.isBlank(apiKey)) {
            throw new IllegalStateException("ApiKey not present. Set in API_KEY environment variable.");
        }

        Eligible.apiKey = apiKey; // eligible public test key
        Eligible.isTest = true;

        defaultCoverageParams.put("payer_id", "00001");
        defaultCoverageParams.put("provider_last_name", "Doe");
        defaultCoverageParams.put("provider_first_name", "John");
        defaultCoverageParams.put("provider_npi", "0123456789");
        defaultCoverageParams.put("member_id", "AETNA00DEP_ACPOSII");
        defaultCoverageParams.put("member_first_name", "IDA");
        defaultCoverageParams.put("member_last_name", "FRANKLIN");
        defaultCoverageParams.put("member_dob", "1701-12-12");
        defaultCoverageParams.put("service_type", "30");

        invalidCoverageParams.put("provider_id", "");

        defaultCoverageMedicareParams.put("provider_last_name", "Doe");
        defaultCoverageMedicareParams.put("provider_first_name", "John");
        defaultCoverageMedicareParams.put("provider_npi", "0123456789");
        defaultCoverageMedicareParams.put("member_id", "77777777A");
        defaultCoverageMedicareParams.put("member_first_name", "IDA");
        defaultCoverageMedicareParams.put("member_last_name", "FRANKLIN");
        defaultCoverageMedicareParams.put("member_dob", "1701-12-12");

        defaultCoverageCostEstimateParams.put("provider_price", "1500.50");
        defaultCoverageCostEstimateParams.put("service_type", "98");
        defaultCoverageCostEstimateParams.put("network", "IN");
        defaultCoverageCostEstimateParams.put("payer_id", "00001");
        defaultCoverageCostEstimateParams.put("provider_last_name", "Doe");
        defaultCoverageCostEstimateParams.put("provider_first_name", "John");
        defaultCoverageCostEstimateParams.put("provider_npi", "0123456789");
        defaultCoverageCostEstimateParams.put("member_id", "COST_ESTIMATES_002");
        defaultCoverageCostEstimateParams.put("member_first_name", "IDA");
        defaultCoverageCostEstimateParams.put("member_last_name", "FRANKLIN");
        defaultCoverageCostEstimateParams.put("member_dob", "1701-12-12");

        defaultClaimAckParams.put("submission_status", "accepted");

        defaultPaymentReportsParams.put("start_date", "2014-02-15");
        defaultPaymentReportsParams.put("end_date", "2020-02-16");

        defaultPaymentStatusParams.put("payer_id", "00001");
        defaultPaymentStatusParams.put("provider_npi", "0123456789");
        defaultPaymentStatusParams.put("provider_tax_id", "111223333");
        defaultPaymentStatusParams.put("member_id", "12312312");
        defaultPaymentStatusParams.put("member_first_name", "IDA");
        defaultPaymentStatusParams.put("member_last_name", "FRANKLIN");
        defaultPaymentStatusParams.put("member_dob", "1701-12-12");
        defaultPaymentStatusParams.put("payer_control_number", "123123123");
        defaultPaymentStatusParams.put("charge_amount", "125.00");
        defaultPaymentStatusParams.put("start_date", "2010-06-15");
        defaultPaymentStatusParams.put("end_date", "2010-06-15");
        defaultPaymentStatusParams.put("trace_number", "BHUYTOK98IK");


        String claimReqJson = new Scanner(resource("claim_request.json", EligibleTest.class))
                .useDelimiter("\\A").next();
        defaultClaimParams = APIResource.GSON.fromJson(claimReqJson, Map.class);

        enrollmentNpisQueryParams.put("status", "accepted");
        enrollmentNpisQueryParams.put("created_before", "2016-04-01");

    }

    @Test
    public void testAPIBase() throws EligibleException {
        assertEquals("https://gds.eligibleapi.com", Eligible.getApiBase());

        String test = "test";
        Eligible.overrideApiBase(test);
        assertEquals(test, Eligible.getApiBase());

        Eligible.overrideApiBase(Eligible.LIVE_API_BASE);
    }

    @Test
    public void testAuthenticationException() throws Exception {
        try {
            Payer.retrieve("FLBLS", RequestOptions.builder().setApiKey("invalid key").build());
        } catch (AuthenticationException e) {
            assertEquals("Could not authenticate you. Please re-try with a valid API key.", e.getMessage());
        }
    }

    @Test
    public void testPayerRetrieve() throws EligibleException {
        Payer payer = Payer.retrieve("FLBLS");

        assertNotNull(payer);
        assertEquals("FLBLS", payer.getId());
        assertEquals("FLBLS", payer.getPayerId());
        assertTrue(payer.getNames().contains("Blue Cross Blue Shield of Florida"));
        assertTrue(payer.getNames().contains("Blue Cross of Florida"));
        assertTrue(payer.getNames().contains("Blue Shield of Florida"));
        assertTrue(payer.getNames().contains("Florida Blue Shield"));
        assertTrue(payer.getNames().contains("BCBS Florida"));
        assertNotNull(payer.getCreatedAt());
        assertNotNull(payer.getUpdatedAt());
        assertNotNull(payer.getSupportedEndpoints());
        assertFalse(payer.getSupportedEndpoints().isEmpty());
    }

    @Test
    public void testPayerRetrieveRawValues() throws EligibleException {
        Payer payer = Payer.retrieve("FLBLS");

        assertNotNull(payer.get("payer_id"));
        assertFalse(payer.getRawValues().isEmpty());
        assertEquals(payer.getPayerId(), payer.get("payer_id"));
        assertEquals(payer.getCreatedAt(), payer.get("created_at"));
        assertEquals(payer.getUpdatedAt(), payer.get("updated_at"));
        assertEquals(payer.getNames(), payer.get("names"));
        assertEquals(payer.getSupportedEndpoints().size(), ((List) payer.get("supported_endpoints")).size());
        assertFalse(payer.getSupportedEndpoints().get(0).getRawValues().isEmpty());
        assertEquals(payer.getSupportedEndpoints().get(0).getRawValues(), ((List) payer.get("supported_endpoints")).get(0));
    }

    @Test
    public void testPayerSearchOptionsAll() throws EligibleException {
        List<Payer.SearchOptions> searchOptionsList = Payer.searchOptions();
        assertNotNull(searchOptionsList);
        assertFalse(searchOptionsList.isEmpty());
        Payer.SearchOptions searchOptions = searchOptionsList.get(0);
        assertNotNull(searchOptions);
        assertNotNull(searchOptions.getPayerId());
        assertNotNull(searchOptions.getSearchOptions());
        assertFalse(searchOptions.getSearchOptions().isEmpty());
    }

    @Test
    public void testPayerSearchOptionsRetrieve() throws EligibleException {
        Payer.SearchOptions searchOptions = Payer.searchOptions("WYMCR");
        assertNotNull(searchOptions);
        assertEquals("WYMCR", searchOptions.getId());
        assertEquals("WYMCR", searchOptions.getPayerId());
        assertNotNull(searchOptions.getSearchOptions());
        assertFalse(searchOptions.getSearchOptions().isEmpty());
    }

    @Test
    public void testPayerRetrieveNullId() throws EligibleException {
        try {
            Payer.retrieve(null);
            fail("Retrieve null Payer didn't result in exception.");
        } catch (InvalidRequestException e) {
            assertEquals("No payer found with payer_id: null", e.getMessage());
        }
    }

    @Test
    public void testPayerRetrieveNullApiKey() throws EligibleException {
        try {
            Payer.retrieve(null, new RequestOptions(null, null, true));
            fail("Using null ApiKey didn't result in exception.");
        } catch (AuthenticationException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testPayerRetrieveInvalidApiKey() throws EligibleException {
        try {
            Payer.retrieve(null, new RequestOptions("invalid api key", null, true));
            fail("Using invalid ApiKey didn't result in exception.");
        } catch (AuthenticationException e) {
            assertEquals("Could not authenticate you. Please re-try with a valid API key.", e.getMessage());
        }
    }

    @Test
    public void testCoverageAll() throws EligibleException {
        for (String memberId : new String[]{
                "AETNA00DEP_ACPOSII", "AETNA00OAMC", "AETNA00AMPPPO", "AETNA00OAAS", "AETNA00HMO", "AETNA00HSAACPII",
                "AEXCEL00DEP_APCPOSII"
        }) {
            defaultCoverageParams.put("member_id", memberId);
            Coverage coverage = Coverage.all(defaultCoverageParams);
            assertNotNull(coverage);
            assertNotNull(coverage.getEligibleId());
            assertNotNull(coverage.getId());
            assertNotNull(coverage.getDemographics());
            assertNotNull(coverage.getInsurance());
            assertNotNull(coverage.getPlan());
            assertNotNull(coverage.getServices());
            assertFalse(coverage.getServices().isEmpty());
            assertNotNull(coverage.getServices().get(0));

            if (!extensiveTesting) {
                break;
            }
        }
    }

    @Test(expected = InvalidRequestException.class)
    public void testFlatternParamsCheck() throws EligibleException {
        Coverage.all(invalidCoverageParams);
    }

    @Test
    public void testEmptyParamsCheck() throws EligibleException {
        try {
            Coverage.all(emptyCoverageParams);
        } catch (InvalidRequestException e) {
            assertEquals("NPI length should be 10 digits, please correct and resubmit.", e.getMessage());
            assertAPIErrorResponseException(e.getCause());
        }
    }


    public static void assertAPIErrorResponseException(Throwable cause) {
        assertNotNull(cause);
        assertTrue("Cause is not an instance of APIErrorResponseException", cause instanceof APIErrorResponseException);
        APIErrorResponseException exception = (APIErrorResponseException) cause;

        assertNotNull(exception.getMessage());
        assertNotNull(exception.getApiResponse());
        assertNotNull(exception.getApiResponse().getEligibleId());
        assertNotNull(exception.getApiResponse().getId());
        assertNotNull(exception.getApiResponse().getCreatedAt());
        assertNotNull(exception.getApiResponse().getError());
        assertNotNull(exception.getApiResponse().getError().getResponseCode());
        assertNotNull(exception.getApiResponse().getError().getResponseDescription());
        assertNotNull(exception.getApiResponse().getError().getDetails());
    }

    @Test
    public void testCoverageAllEmptyParams() throws EligibleException {
        try {
            Map<String, Object> invalidParams = new HashMap<String, Object>();
            Coverage coverage = Coverage.all(invalidParams);
            fail("API call didn't throw exception on empty params");
        } catch (InvalidRequestException e) {
            assertNotNull(e.getMessage());
            assertAPIErrorResponseException(e.getCause());
        }
    }

    @Test
    public void testCoverageAllInvalidParams() throws EligibleException {
        try {
            Map<String, Object> invalidParams = new HashMap<String, Object>(defaultCoverageParams);
            invalidParams.put("provider_npi", "ABC3456789"); // Non Numeric Characters in NPI
            Coverage coverage = Coverage.all(invalidParams);
            fail("API call didn't throw exception on invalid params");
        } catch (InvalidRequestException e) {
            assertNotNull(e.getMessage());
            assertAPIErrorResponseException(e.getCause());
        }
    }

    @Test
    public void testCoverageMedicare() throws EligibleException {
        for (String memberId : new String[]{
                "77777777A", "111111111A", "121111211A", "333333333A", "4444444444A", "4747474747A", "9999999999A",
        }) {
            defaultCoverageMedicareParams.put("member_id", memberId);
            Coverage.Medicare medicareCoverage = Coverage.medicare(defaultCoverageMedicareParams);
            assertNotNull(medicareCoverage);
            assertNotNull(medicareCoverage.getEligibleId());
            assertNotNull(medicareCoverage.getId());
            assertNotNull(medicareCoverage.getLastName());
            assertNotNull(medicareCoverage.getFirstName());
            assertNotNull(medicareCoverage.getMemberId());
            assertNotNull(medicareCoverage.getGender());
            assertNotNull(medicareCoverage.getPayerName());
            assertNotNull(medicareCoverage.getPlanNumber());
            assertNotNull(medicareCoverage.getEligibiltyDates());
            assertNotNull(medicareCoverage.getPlanTypes());
            assertFalse(medicareCoverage.getPlanTypes().isEmpty());
            assertNotNull(medicareCoverage.getPlanDetails());
            assertFalse(medicareCoverage.getPlanDetails().isEmpty());

            if (!extensiveTesting) {
                break;
            }
        }
    }

    @Test
    public void testCoverageCostEstimate() throws EligibleException {
        for (String memberId : new String[]{
                "COST_ESTIMATES_002", "COST_ESTIMATES_001", "COST_ESTIMATES_003", "COST_ESTIMATES_004",
                "COST_ESTIMATES_005",
        }) {
            defaultCoverageCostEstimateParams.put("member_id", memberId);
            Coverage.CostEstimates costEstimates = Coverage.costEstimate(defaultCoverageCostEstimateParams);
            assertNotNull(costEstimates);
            assertNotNull(costEstimates.getEligibleId());
            assertNotNull(costEstimates.getId());
            assertNotNull(costEstimates.getDemographics());
            assertNotNull(costEstimates.getInsurance());
            assertNotNull(costEstimates.getPlan());
            assertNotNull(costEstimates.getServices());
            assertFalse(costEstimates.getServices().isEmpty());
            assertNotNull(costEstimates.getServices().get(0));
            assertNotNull(costEstimates.getCostEstimates());
            assertFalse(costEstimates.getCostEstimates().isEmpty());
            assertNotNull(costEstimates.getCostEstimates().get(0));

            if (!extensiveTesting) {
                break;
            }
        }
    }

    @Test
    public void testClaim() throws EligibleException {
        Claim claim = Claim.create(defaultClaimParams);
        assertNotNull(claim);
        assertNotNull(claim.getReferenceId());
        assertNotNull(claim.getId());
        assertNotNull(claim.getSuccess());
        assertNotNull(claim.getCreatedAt());
    }

    @Test
    public void testGetAcknowledgements() throws EligibleException {
        Claim.Acknowledgements acknowledgements = Claim.getAcknowledgements(defaultClaimAckReferenceId);
        assertNotNull(acknowledgements);
        assertNotNull(acknowledgements.getReferenceId());
        assertNotNull(acknowledgements.getId());
        assertNotNull(acknowledgements.getPayerControlNumber());
        assertNotNull(acknowledgements.getPage());
        assertNotNull(acknowledgements.getPerPage());
        assertNotNull(acknowledgements.getNumOfPages());
        assertNotNull(acknowledgements.getTotal());
        assertNotNull(acknowledgements.getAcknowledgements());
        assertFalse(acknowledgements.getAcknowledgements().isEmpty());
        assertFalse(acknowledgements.getAcknowledgements().get(1).getMessage().isEmpty());
        assertNotNull(acknowledgements.getAcknowledgements().get(1).getStatus());
    }

    @Test
    public void testQueryAcknowledgements() throws EligibleException {
        Claim.Acknowledgements acknowledgements = Claim.queryAcknowledgements(defaultClaimAckParams);
        assertNotNull(acknowledgements);
        assertNotNull(acknowledgements.getPage());
        assertNotNull(acknowledgements.getPerPage());
        assertNotNull(acknowledgements.getNumOfPages());
        assertNotNull(acknowledgements.getTotal());
        assertNotNull(acknowledgements.getAcknowledgements());
        assertFalse(acknowledgements.getAcknowledgements().isEmpty());
    }

    @Test
    public void testGetPaymentReport() throws EligibleException {
        Claim.PaymentReport report = Claim.getPaymentReport(defaultClaimPaymentReportClaimReferenceId);
        assertNotNull(report);
        assertNotNull(report.getReferenceId());
        assertNotNull(report.getId());
        assertNotNull(report.getEffectiveDate());
        assertNotNull(report.getPayer());
        assertNotNull(report.getFinancials());
        assertNotNull(report.getPayee());
        assertNotNull(report.getPatient());
        assertNotNull(report.getCorrectedPatient());
        assertNotNull(report.getOtherPatient());
        assertNotNull(report.getClaim());
    }

    @Test
    public void testGetPaymentReportWithId() throws EligibleException {
        Claim.PaymentReport report = Claim.getPaymentReport(defaultClaimPaymentReportClaimReferenceId, defaultClaimPaymentReportId);
        assertNotNull(report);
        assertNotNull(report.getReferenceId());
        assertNotNull(report.getId());
        assertNotNull(report.getEffectiveDate());
        assertNotNull(report.getPayer());
        assertNotNull(report.getFinancials());
        assertNotNull(report.getPayee());
        assertNotNull(report.getPatient());
        assertNotNull(report.getCorrectedPatient());
        assertNotNull(report.getOtherPatient());
        assertNotNull(report.getClaim());
    }

    @Test
    public void testQueryPaymentReports() throws EligibleException {
        Claim.PaymentReports reports = Claim.queryPaymentReports(defaultPaymentReportsParams);
        assertNotNull(reports);
        assertNotNull(reports.getPage());
        assertNotNull(reports.getId());
        assertNotNull(reports.getPerPage());
        assertNotNull(reports.getNumOfPages());
        assertNotNull(reports.getTotal());
        assertNotNull(reports.getReports());
        assertFalse(reports.getReports().isEmpty());
        assertNotNull(reports.getReports().get(0));
        assertNotNull(reports.getReports().get(0).getReferenceId());
    }

    @Test
    public void testPaymentStatus() throws EligibleException {
        for (String memberId : new String[]{
                "12312312", "10101010", "34343434", "45454545"
        }) {
            defaultPaymentStatusParams.put("member_id", memberId);
            PaymentStatus status = PaymentStatus.retrieve(defaultPaymentStatusParams);
            assertNotNull(status);
            assertNotNull(status.getEligibleId());
            assertNotNull(status.getId());
            assertNotNull(status.getCreatedAt());
            assertNotNull(status.getPayer());
            assertNotNull(status.getServiceProvider());
            assertFalse(status.getServiceProvider().isEmpty());
            assertNotNull(status.getServiceProvider().get(0));
            assertNotNull(status.getPatients());
            assertFalse(status.getPatients().isEmpty());
            assertNotNull(status.getPatients().get(0));
            assertNotNull(status.getClaims());
            assertFalse(status.getClaims().isEmpty());
            assertNotNull(status.getClaims().get(0));

            if (!extensiveTesting) {
                break;
            }
        }
    }

    public static Map<String, Object> createEnrollmentParams() throws Exception {
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

    public static void assertEnrollmentNpiResponse(EnrollmentNpiResponse enrollmentNpiResponse) {
        assertNotNull(enrollmentNpiResponse);
        EnrollmentNpi enrollmentNpi = enrollmentNpiResponse.getEnrollmentNpi();
        assertNotNull(enrollmentNpi);
        assertNotNull(enrollmentNpi.getId());
        assertNotNull(enrollmentNpi.getAddress());
        assertNotNull(enrollmentNpi.getCity());
        assertNotNull(enrollmentNpi.getZip());
        assertNotNull(enrollmentNpi.getEffectiveDate());
        assertNotNull(enrollmentNpi.getFacilityName());
        assertNotNull(enrollmentNpi.getMedicaidId());
        assertNotNull(enrollmentNpi.getNpi());
        assertNotNull(enrollmentNpi.getProviderName());
        assertNotNull(enrollmentNpi.getPtan());
        assertNotNull(enrollmentNpi.getState());
        assertNotNull(enrollmentNpi.getStatus());
        assertNotNull(enrollmentNpi.getTaxId());
        assertNotNull(enrollmentNpi.getCreatedAt());
        assertNotNull(enrollmentNpi.getUpdatedAt());

        AuthorizedSigner authorizedSigner = enrollmentNpi.getAuthorizedSigner();
        assertNotNull(authorizedSigner);
        assertNotNull(authorizedSigner.getContactNumber());
        assertNotNull(authorizedSigner.getEmail());
        assertNotNull(authorizedSigner.getFirstName());
        assertNotNull(authorizedSigner.getLastName());
        assertNotNull(authorizedSigner.getTitle());

        AuthorizedSigner.Signature signature = authorizedSigner.getSignature();
        assertNotNull(signature);
        assertNotNull(signature.getCoordinates());

        for (AuthorizedSigner.Coordinate coordinate : signature.getCoordinates()) {
            assertNotNull(coordinate);
            assertNotNull(coordinate.getLx());
            assertNotNull(coordinate.getLy());
            assertNotNull(coordinate.getMx());
            assertNotNull(coordinate.getMy());
        }

        com.eligible.model.enrollmentnpi.Payer payer = enrollmentNpi.getPayer();
        assertNotNull(payer);
        assertNotNull(payer.getId());
        assertNotNull(payer.getEndpoints());
        assertNotNull(payer.getNames());
    }

    public static void assertReceivedPdf(EnrollmentNpi.ReceivedPdf receivedPdf) {
        assertPdfResource(receivedPdf);
        assertNotNull(receivedPdf.getNotificationMessage());
    }

    public static void assertPdfResource(EnrollmentNpi.PdfResource pdfResource) {
        assertNotNull(pdfResource);
        assertNotNull(pdfResource.getName());
        assertNotNull(pdfResource.getCreatedAt());
        assertNotNull(pdfResource.getUpdatedAt());
        assertNotNull(pdfResource.getDownloadUrl());
    }

    @Test
    public void testCreateEnrollment() throws Exception {
        EnrollmentNpiResponse enrollment = EnrollmentNpi.create(createEnrollmentParams());
        assertEnrollmentNpiResponse(enrollment);
    }

    @Test
    public void testRetrieveEnrollmentNpi() throws Exception {
        EnrollmentNpiResponse enrollment = EnrollmentNpi.retrieve("557604291");
        assertEnrollmentNpiResponse(enrollment);
        assertPdfResource(enrollment.getEnrollmentNpi().getOriginalSignaturePdf());
        assertReceivedPdf(enrollment.getEnrollmentNpi().getReceivedPdf());
    }

    @Test(expected = InvalidRequestException.class)
    public void testUpdateEnrollmentNpi() throws Exception {
        EnrollmentNpi.update("557604291", createEnrollmentParams());
        // throw error
    }

    @Test
    public void testQueryEnrollmentNpi() throws Exception {
        EnrollmentNpiQueryResponse enrollmentNpisQueryResponse = EnrollmentNpi.query(enrollmentNpisQueryParams);
        assertNotNull(enrollmentNpisQueryResponse);
        assertNotNull(enrollmentNpisQueryResponse.getPage());
        assertNotNull(enrollmentNpisQueryResponse.getPerPage());
        assertNotNull(enrollmentNpisQueryResponse.getNumOfPages());
        assertNotNull(enrollmentNpisQueryResponse.getTotal());
        assertNotNull(enrollmentNpisQueryResponse.getEnrollmentNpis());

        for (EnrollmentNpiResponse enrollmentNpi : enrollmentNpisQueryResponse.getEnrollmentNpis()) {
            assertEnrollmentNpiResponse(enrollmentNpi);
            assertPdfResource(enrollmentNpi.getEnrollmentNpi().getOriginalSignaturePdf());
            assertReceivedPdf(enrollmentNpi.getEnrollmentNpi().getReceivedPdf());
        }
    }

    @Test
    public void testRetrieveReceivedPdf() throws Exception {
        ReceivedPdfResponse receivedPdf = EnrollmentNpi.getReceivedPdf("557604291");
        assertNotNull(receivedPdf);
        assertReceivedPdf(receivedPdf.getReceivedPdf());
    }

    @Test
    public void testDownloadReceivedPdf() throws Exception {
        String receivedPdf = EnrollmentNpi.downloadReceivedPdf("557604291");
        assertEquals("PDF file stored at /tmp/received_pdf.pdf", receivedPdf);
    }

    @Test
    public void testCreateOriginalSignaturePdf() throws Exception {
        String fileName = "./src/test/resources/com/eligible/EligibleSandboxEnrollmentPDF.pdf";
        OriginalSignaturePdfResponse originalSignaturePdf = EnrollmentNpi.createOriginalSignaturePdf("557604291", fileName);
        assertNotNull(originalSignaturePdf);
        assertPdfResource(originalSignaturePdf.getOriginalSignaturePdf());
    }

    @Test
    public void testRetrieveOriginalSignaturePdf() throws Exception {
        OriginalSignaturePdfResponse originalSignaturePdf = EnrollmentNpi.getOriginalSignaturePdf("557604291");
        assertNotNull(originalSignaturePdf);
        assertPdfResource(originalSignaturePdf.getOriginalSignaturePdf());
    }

    @Test
    public void testUpdateOriginalSignaturePdf() throws Exception {
        String fileName = "./src/test/resources/com/eligible/EligibleSandboxEnrollmentPDF.pdf";
        OriginalSignaturePdfResponse originalSignaturePdf = EnrollmentNpi.updateOriginalSignaturePdf("557604291", fileName);
        assertNotNull(originalSignaturePdf);
        assertPdfResource(originalSignaturePdf.getOriginalSignaturePdf());
    }

    @Test
    public void testDownloadOriginalSignaturePdf() throws Exception {
        String originalSignaturePdf = EnrollmentNpi.downloadOriginalSignaturePdf("557604291");
        assertEquals("PDF file stored at /tmp/original_signature_pdf.pdf", originalSignaturePdf);
    }

    @Test
    public void testDeleteOriginalSignaturePdf() throws Exception {
        OriginalSignaturePdfDeleteResponse originalSignaturePdf = EnrollmentNpi.deleteOriginalSignaturePdf("557604291");
        assertNotNull(originalSignaturePdf);
        assertEquals("Original Signature Pdf deleted", originalSignaturePdf.getMessage());
    }

}
