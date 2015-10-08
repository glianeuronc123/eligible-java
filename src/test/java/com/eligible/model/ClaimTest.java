package com.eligible.model;

import com.eligible.BaseMockedNetwokEligibleTest;
import com.eligible.exception.EligibleException;
import org.junit.Test;

import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ClaimTest extends BaseMockedNetwokEligibleTest {

    @Test
    public void testAll() throws EligibleException {
        Claim.create(DUMMY_PARAMS);

        verifyPost(Claim.class, "https://gds.eligibleapi.com/v1.5/claims", DUMMY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testCreate() throws EligibleException {
        Claim.create(DUMMY_PARAMS);

        verifyPost(Claim.class, "https://gds.eligibleapi.com/v1.5/claims", DUMMY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testGetAcknowledgements() throws EligibleException {
        Claim.getAcknowledgements(DUMMY_REFERENCE_ID);

        verifyGet(Claim.Acknowledgements.class, "https://gds.eligibleapi.com/v1.5/claims/dummy_reference_id/acknowledgements");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testQueryAcknowledgements() throws EligibleException {
        Claim.queryAcknowledgements(DUMMY_PARAMS);

        verifyGet(Claim.Acknowledgements.class, "https://gds.eligibleapi.com/v1.5/claims/acknowledgements", DUMMY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testGetPaymentReport() throws EligibleException {
        Claim.getPaymentReport(DUMMY_REFERENCE_ID);

        verifyGet(Claim.PaymentReport.class, "https://gds.eligibleapi.com/v1.5/claims/dummy_reference_id/payment_reports");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testGetPaymentReportWithId() throws EligibleException {
        Claim.getPaymentReport(DUMMY_REFERENCE_ID, DUMMY_REFERENCE_ID);

        verifyGet(Claim.PaymentReport.class, "https://gds.eligibleapi.com/v1.5/claims/dummy_reference_id/payment_reports/dummy_reference_id");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testQueryPaymentReports() throws EligibleException {
        Claim.queryPaymentReports(DUMMY_PARAMS);

        verifyGet(Claim.PaymentReports.class, "https://gds.eligibleapi.com/v1.5/claims/payment_reports", DUMMY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

}
