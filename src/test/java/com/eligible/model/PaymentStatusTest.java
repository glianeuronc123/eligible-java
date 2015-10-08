package com.eligible.model;

import com.eligible.BaseMockedNetwokEligibleTest;
import com.eligible.exception.EligibleException;
import org.junit.Test;

import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PaymentStatusTest extends BaseMockedNetwokEligibleTest {

    @Test
    public void testRetrieve() throws EligibleException {
        PaymentStatus.retrieve(DUMMY_PARAMS);

        verifyGet(PaymentStatus.class, "https://gds.eligibleapi.com/v1.5/payment/status", DUMMY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }
}
