package com.eligible.model;

import com.eligible.BaseMockedNetwokEligibleTest;
import com.eligible.exception.EligibleException;
import org.junit.Test;

import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ClaimTest extends BaseMockedNetwokEligibleTest {

    @Test
    public void testAll() throws EligibleException {
        Claim.all(DUMMAY_PARAMS);

        verifyPost(Claim.class, "https://gds.eligibleapi.com/v1.5/claims", DUMMAY_PARAMS);
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
        Claim.queryAcknowledgements(DUMMAY_PARAMS);

        verifyGet(Claim.Acknowledgements.class, "https://gds.eligibleapi.com/v1.5/claims/acknowledgements", DUMMAY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testGetPaymentReports() throws EligibleException {
        Claim.getPaymentReports(DUMMY_REFERENCE_ID);

        verifyGet(Claim.PaymentReport.class, "https://gds.eligibleapi.com/v1.5/claims/dummy_reference_id/payment_reports");
        verifyNoMoreInteractions(networkMock);
    }

}
