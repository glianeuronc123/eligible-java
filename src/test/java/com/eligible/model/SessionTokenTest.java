package com.eligible.model;

import com.eligible.BaseMockedNetwokEligibleTest;
import com.eligible.exception.EligibleException;
import org.junit.Test;

import static org.mockito.Mockito.verifyNoMoreInteractions;

public class SessionTokenTest extends BaseMockedNetwokEligibleTest {

    @Test
    public void testGet() throws EligibleException {
        SessionToken.get(DUMMY_PARAMS);

        verifyPost(SessionToken.class, "https://gds.eligibleapi.com/v1.5/session_tokens/create", DUMMY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

}
