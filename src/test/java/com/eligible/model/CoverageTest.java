package com.eligible.model;

import com.eligible.BaseEligibleTest;
import com.eligible.exception.EligibleException;
import com.eligible.net.APIResource;
import com.eligible.net.LiveEligibleResponseGetter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CoverageTest extends BaseEligibleTest {

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
    public void testCoverage() throws EligibleException {
        Coverage.all(DUMMAY_PARAMS);

        verifyGet(Coverage.class, "https://gds.eligibleapi.com/v1.5/coverage/all", DUMMAY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testMedicare() throws EligibleException {
        Coverage.medicare(DUMMAY_PARAMS);

        verifyGet(Coverage.Medicare.class, "https://gds.eligibleapi.com/v1.5/coverage/medicare", DUMMAY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testCostEstimate() throws EligibleException {
        Coverage.costEstimate(DUMMAY_PARAMS);

        verifyGet(Coverage.CostEstimates.class, "https://gds.eligibleapi.com/v1.5/coverage/cost_estimates", DUMMAY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

}
