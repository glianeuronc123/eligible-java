package com.eligible.model;

import com.eligible.BaseMockedNetwokEligibleTest;
import com.eligible.exception.EligibleException;
import org.junit.Test;

import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ClaimTest extends BaseMockedNetwokEligibleTest {

    @Test
    public void testAll() throws EligibleException {
        Claim.all(DUMMAY_PARAMS);

        verifyPost(Claim.class, "https://gds.eligibleapi.com/v1.5/claims");
        verifyNoMoreInteractions(networkMock);
    }

}
