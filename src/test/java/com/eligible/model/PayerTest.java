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

public class PayerTest extends BaseEligibleTest {

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
    public void testRetrieve() throws EligibleException {
        Payer.retrieve("payer_id");

        verifyGet(Payer.class, "https://gds.eligibleapi.com/v1.5/payers/payer_id");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testAll() throws EligibleException {
        Payer.all();

        verifyGet(new TypeToken<List<Payer>>(){}.getType(), "https://gds.eligibleapi.com/v1.5/payers");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testAllSearchOptions() throws EligibleException {
        Payer.searchOptions();

        verifyGet(new TypeToken<List<Payer.SearchOptions>>(){}.getType(), "https://gds.eligibleapi.com/v1.5/payers/search_options");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testSearchOptions() throws EligibleException {
        Payer.searchOptions("payer_id");

        verifyGet(Payer.SearchOptions.class, "https://gds.eligibleapi.com/v1.5/payers/payer_id/search_options");
        verifyNoMoreInteractions(networkMock);
    }


    public static class NetworkStreamMockTests extends BaseEligibleTest {

        @Before
        public void mockEligibleResponseGetter() {
            System.setProperty(CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME, MockURLStreamHandler.class.getName());
        }

        @After
        public void unmockEligibleResponseGetter() {
        /* This needs to be done because tests aren't isolated in Java */
            System.clearProperty(CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME);
        }

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
