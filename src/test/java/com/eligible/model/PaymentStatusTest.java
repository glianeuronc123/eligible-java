package com.eligible.model;

import com.eligible.BaseMockedNetwokEligibleTest;
import com.eligible.BaseMockedNetwokStreamEligibleTest;
import com.eligible.exception.APIException;
import com.eligible.exception.EligibleException;
import org.junit.Test;

import static com.eligible.EligibleTest.assertAPIErrorResponseException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PaymentStatusTest extends BaseMockedNetwokEligibleTest {

    @Test
    public void testRetrieve() throws EligibleException {
        PaymentStatus.retrieve(DUMMY_PARAMS);

        verifyGet(PaymentStatus.class, "https://gds.eligibleapi.com/v1.5/payment/status", DUMMY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }


    public static class NetworkStreamMockTest extends BaseMockedNetwokStreamEligibleTest {

        @Test
        public void testRetrieveException() throws Exception {
            stubNetworkStream(resource("payment_status_error.json"));

            try {
                PaymentStatus paymentStatus = PaymentStatus.retrieve(DUMMY_PARAMS);
                fail("Error Json Returned Success to user" + paymentStatus);
            } catch (APIException e) {
                assertNotNull(e.getMessage());
                assertAPIErrorResponseException(e.getCause());
            }
        }
    }
}
