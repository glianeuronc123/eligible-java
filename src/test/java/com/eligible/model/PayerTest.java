package com.eligible.model;

import com.eligible.BaseMockedNetwokEligibleTest;
import com.eligible.BaseMockedNetwokStreamEligibleTest;
import com.eligible.exception.EligibleException;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PayerTest extends BaseMockedNetwokEligibleTest {

    @Test
    public void testRetrieve() throws EligibleException {
        Payer.retrieve("payer_id");

        verifyGet(Payer.class, "https://gds.eligibleapi.com/v1.5/payers/payer_id");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testAll() throws EligibleException {
        Payer.all();

        verifyGet(new TypeToken<List<Payer>>() { }.getType(), "https://gds.eligibleapi.com/v1.5/payers");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testAllWithParams() throws EligibleException {
        Payer.all(DUMMY_PARAMS);

        verifyGet(new TypeToken<List<Payer>>() { }.getType(), "https://gds.eligibleapi.com/v1.5/payers", DUMMY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testAllSearchOptions() throws EligibleException {
        Payer.searchOptions();

        verifyGet(new TypeToken<List<Payer.SearchOptions>>() { }.getType(), "https://gds.eligibleapi.com/v1.5/payers/search_options");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testSearchOptions() throws EligibleException {
        Payer.searchOptions("payer_id");

        verifyGet(Payer.SearchOptions.class, "https://gds.eligibleapi.com/v1.5/payers/payer_id/search_options");
        verifyNoMoreInteractions(networkMock);
    }


    public static class NetworkStreamMockTest extends BaseMockedNetwokStreamEligibleTest {

        @Test
        public void testPayersAll() throws Exception {
            stubNetworkStream(resource("payers.json"));

            List<Payer> payers = Payer.all();
            Payer flblsPayer = null;
            for (Payer payer : payers) {
                if (payer.getPayerId().equals("FLBLS")) {
                    flblsPayer = payer;
                }
            }

            assertNotNull(flblsPayer);
            assertEquals("FLBLS", flblsPayer.getId());
            assertEquals("FLBLS", flblsPayer.getPayerId());
            assertTrue(flblsPayer.getNames().contains("Blue Cross Blue Shield of Florida"));
            assertTrue(flblsPayer.getNames().contains("Blue Cross of Florida"));
            assertTrue(flblsPayer.getNames().contains("Blue Shield of Florida"));
            assertTrue(flblsPayer.getNames().contains("Florida Blue Shield"));
            assertTrue(flblsPayer.getNames().contains("BCBS Florida"));
            assertNotNull(flblsPayer.getCreatedAt());
            assertNotNull(flblsPayer.getUpdatedAt());
            assertNotNull(flblsPayer.getSupportedEndpoints());
            assertFalse(flblsPayer.getSupportedEndpoints().isEmpty());
        }
    }

}
