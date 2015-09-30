package com.eligible.model;

import com.eligible.BaseEligibleTest;
import com.eligible.exception.EligibleException;
import com.eligible.net.APIResource;
import com.eligible.net.LiveEligibleResponseGetter;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.eligible.net.LiveEligibleResponseGetter.CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ClaimTest extends BaseEligibleTest {

    @Before
    public void mockEligibleResponseGetter() {
        APIResource.setEligibleResponseGetter(networkMock);
    }

    @After
    public void unmockEligibleResponseGetter() {
        /* This needs to be done because tests aren't isolated in Java */
        APIResource.setEligibleResponseGetter(new LiveEligibleResponseGetter());
    }

    @Test
    public void testAll() throws EligibleException {
        Claim.all(DUMMAY_PARAMS);

        verifyPost(Claim.class, "https://gds.eligibleapi.com/v1.5/claims");
        verifyNoMoreInteractions(networkMock);
    }

}
