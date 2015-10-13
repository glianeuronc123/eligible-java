package com.eligible;

import com.eligible.exception.APIErrorResponseException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.EligibleException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.model.Claim;
import com.eligible.model.Coverage;
import com.eligible.model.Payer;
import com.eligible.model.PaymentStatus;
import com.eligible.net.APIResource;
import com.eligible.net.RequestOptions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.eligible.util.TestUtil.resource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Main API Tests for running real Sandbox API calls.
 */
public class EligibleTest {
    static Map<String, Object> defaultCoverageParams = new HashMap<String, Object>();
    static Map<String, Object> invalidCoverageParams = new HashMap<String, Object>();
    static Map<String, Object> emptyCoverageParams = Collections.emptyMap();
    static Map<String, Object> defaultCoverageMedicareParams = new HashMap<String, Object>();
    static Map<String, Object> defaultCoverageCostEstimateParams = new HashMap<String, Object>();
    static Map<String, Object> defaultClaimParams = new HashMap<String, Object>();
    static String defaultClaimAckReferenceId = "12121212";
    static Map<String, Object> defaultClaimAckParams = new HashMap<String, Object>();
    static String defaultClaimPaymentReportClaimReferenceId = "BDA85HY09IJ";
    static String defaultClaimPaymentReportId = "ABX45DGER44";
    static Map<String, Object> defaultPaymentReportsParams = new HashMap<String, Object>();
    static Map<String, Object> defaultPaymentStatusParams = new HashMap<String, Object>();

    @Before
    public void before() {
    }

    @BeforeClass
    public static void setUp() throws Exception {
        Eligible.apiKey = "n5Cddnj2KST6YV9J2l2ztQQ2VrdPfzA4JPbn"; // eligible public test key
        Eligible.isTest = true;

        defaultCoverageParams.put("payer_id", "00001");
        defaultCoverageParams.put("provider_last_name", "Doe");
        defaultCoverageParams.put("provider_first_name", "John");
        defaultCoverageParams.put("provider_npi", "0123456789");
        defaultCoverageParams.put("member_id", "ZZZ445554301");
        defaultCoverageParams.put("member_first_name", "IDA");
        defaultCoverageParams.put("member_last_name", "FRANKLIN");
        defaultCoverageParams.put("member_dob", "1701-12-12");
        defaultCoverageParams.put("service_type", "30");

        invalidCoverageParams.put("provider_id", "");

        defaultCoverageMedicareParams.put("provider_last_name", "Doe");
        defaultCoverageMedicareParams.put("provider_first_name", "John");
        defaultCoverageMedicareParams.put("provider_npi", "0123456789");
        defaultCoverageMedicareParams.put("member_id", "ZZZ445554301");
        defaultCoverageMedicareParams.put("member_first_name", "IDA");
        defaultCoverageMedicareParams.put("member_last_name", "FRANKLIN");
        defaultCoverageMedicareParams.put("member_dob", "1701-12-12");

        defaultCoverageCostEstimateParams.put("provider_price", "1500.50");
        defaultCoverageCostEstimateParams.put("service_type", "1");
        defaultCoverageCostEstimateParams.put("network", "IN");
        defaultCoverageCostEstimateParams.put("payer_id", "00001");
        defaultCoverageCostEstimateParams.put("provider_last_name", "Doe");
        defaultCoverageCostEstimateParams.put("provider_first_name", "John");
        defaultCoverageCostEstimateParams.put("provider_npi", "0123456789");
        defaultCoverageCostEstimateParams.put("member_id", "ZZZ445554301");
        defaultCoverageCostEstimateParams.put("member_first_name", "IDA");
        defaultCoverageCostEstimateParams.put("member_last_name", "FRANKLIN");
        defaultCoverageCostEstimateParams.put("member_dob", "1701-12-12");

        defaultClaimAckParams.put("internal_id", "12345");
        defaultClaimAckParams.put("submission_status", "accepted");
        defaultClaimAckParams.put("internal_id", "12345");
        defaultClaimAckParams.put("claim_submitted_date", "2014-02-15");

        defaultPaymentReportsParams.put("internal_id", "12345");
        defaultPaymentReportsParams.put("start_date", "2014-02-15");
        defaultPaymentReportsParams.put("end_date", "2014-02-16");

        defaultPaymentStatusParams.put("payer_id", "00001");
        defaultPaymentStatusParams.put("provider_npi", "0123456789");
        defaultPaymentStatusParams.put("provider_tax_id", "111223333");
        defaultPaymentStatusParams.put("member_id", "ZZZ445554301");
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
        Coverage.Medicare medicareCoverage = Coverage.medicare(defaultCoverageMedicareParams);
        assertNotNull(medicareCoverage);
        assertNotNull(medicareCoverage.getEligibleId());
        assertNotNull(medicareCoverage.getId());
        assertNotNull(medicareCoverage.getLastName());
        assertNotNull(medicareCoverage.getFirstName());
        assertNotNull(medicareCoverage.getMemberId());
        assertNotNull(medicareCoverage.getGroupId());
        assertNotNull(medicareCoverage.getGroupName());
        assertNotNull(medicareCoverage.getGender());
        assertNotNull(medicareCoverage.getPayerName());
        assertNotNull(medicareCoverage.getPlanNumber());
        assertNotNull(medicareCoverage.getEligibiltyDates());
        assertNotNull(medicareCoverage.getPlanTypes());
        assertFalse(medicareCoverage.getPlanTypes().isEmpty());
        assertNotNull(medicareCoverage.getPlanDetails());
        assertFalse(medicareCoverage.getPlanDetails().isEmpty());
    }

    @Test
    public void testCoverageCostEstimate() throws EligibleException {
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
        assertNotNull(report.getServiceProvider());
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
        assertNotNull(report.getServiceProvider());
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
    }

}
