package com.eligible.model;

import com.eligible.BaseEligibleTest;
import com.eligible.exception.APIErrorResponseException;
import com.eligible.json.deserializer.EligibleObjectTypeAdapterFactory;
import com.eligible.model.coverage.Dates;
import com.eligible.model.enrollmentnpi.EnrollmentNpiQueryResponse;
import com.eligible.model.enrollmentnpi.EnrollmentNpiResponse;
import com.eligible.model.enrollmentnpi.OriginalSignaturePdfDeleteResponse;
import com.eligible.model.enrollmentnpi.OriginalSignaturePdfResponse;
import com.eligible.model.enrollmentnpi.ReceivedPdfResponse;
import com.eligible.net.APIResource;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static org.junit.Assert.fail;

public class DeserializerTest extends BaseEligibleTest {

    private static Gson apiGson = APIResource.GSON;

    @Test
    public void testDeserializeDates() throws Exception {
        apiGson.fromJson(resource("dates.json"), Dates.class);     // should not throw error
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserializeDatesException() throws Exception {
        apiGson.fromJson("{}", Dates.class);                       // should throw error
    }

    @Test
    public void testDeserializeError() throws Exception {
        Gson gson = EligibleObjectTypeAdapterFactory.GSON;
        APIErrorResponse response = gson.fromJson(resource("api_error_response.json"), APIErrorResponse.class);     // should not throw error
        Assert.assertNotNull(response.getError());
        Assert.assertNotNull(response.getError().getFollowUpActionCode());
        Assert.assertNotNull(response.getError().getFollowUpActionDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserializeErrorException() throws Exception {
        Gson gson = EligibleObjectTypeAdapterFactory.GSON;
        gson.fromJson("{\"error\":[]}", APIErrorResponse.class);                       // should throw error
    }

    @Test
    public void testDeserializeErrorResponse() throws Exception {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        testObjectDeserialize(gson, "payment_status_error.json", APIErrorResponse.class);
    }

    @Test (expected = APIErrorResponseException.class)
    public void testDeserializeErrorResponseException() throws Exception {
        apiGson.fromJson(resource("payment_status_error.json"), EligibleObject.class);
    }

    @Test
    public void testDeserializePayerList() throws Exception {
        Type listPayerType = new TypeToken<List<Payer>>() { }.getType();
        testListDeserialize(apiGson, "payers.json", listPayerType);
    }

    @Test
    public void testDeserializePayer() throws Exception {
        testObjectDeserialize(apiGson, "payer.json", Payer.class);
    }

    @Test
    public void testDeserializePayerRawValues() throws Exception {
        Payer payer = testObjectDeserialize(apiGson, "payer.json", Payer.class);
        Assert.assertNotNull(payer.get("payer_id"));
        Assert.assertEquals(payer.getPayerId(), payer.get("payer_id"));
        Assert.assertEquals(payer.getCreatedAt(), payer.get("created_at"));
        Assert.assertEquals(payer.getUpdatedAt(), payer.get("updated_at"));
        Assert.assertEquals(payer.getNames(), payer.get("names"));
        Assert.assertEquals(payer.getSupportedEndpoints().size(), ((List) payer.get("supported_endpoints")).size());
        Assert.assertEquals(payer.getSupportedEndpoints().get(0).getRawValues(), ((List) payer.get("supported_endpoints")).get(0));
    }

    @Test
    public void testDeserializeSearchOptionsList() throws Exception {
        Type listSearchOptionsType = new TypeToken<List<Payer.SearchOptions>>() { }.getType();
        testListDeserialize(apiGson, "search_options_list.json", listSearchOptionsType);
    }

    @Test
    public void testDeserializeSearchOptions() throws Exception {
        testObjectDeserialize(apiGson, "search_options.json", Payer.SearchOptions.class);
    }

    @Test
    public void testDeserializeCoverage() throws Exception {
        testObjectDeserialize(apiGson, "coverage.json", Coverage.class);
    }

    @Test
    public void testDeserializeCoverageCostEstimate() throws Exception {
        testObjectDeserialize(apiGson, "cost_estimate.json", Coverage.CostEstimates.class);
    }

    @Test
    public void testDeserializeMedicareCoverage() throws Exception {
        testObjectDeserialize(apiGson, "medicare_coverage.json", Coverage.Medicare.class);
    }

    @Test
    public void testDeserializeClaim() throws Exception {
        testObjectDeserialize(apiGson, "claim.json", Claim.class);
    }

    @Test
    public void testDeserializeClaimAcknowledgement() throws Exception {
        testObjectDeserialize(apiGson, "acknowledgement.json", Claim.Acknowledgements.class);
    }

    @Test
    public void testDeserializeClaimAcknowledgements() throws Exception {
        testObjectDeserialize(apiGson, "acknowledgements.json", Claim.Acknowledgements.class);
    }

    @Test
    public void testDeserializeClaimPaymentReport() throws Exception {
        testObjectDeserialize(apiGson, "payment_report.json", Claim.PaymentReport.class);
    }

    @Test
    public void testDeserializeClaimPaymentReports() throws Exception {
        testObjectDeserialize(apiGson, "payment_reports.json", Claim.PaymentReports.class);
    }

    @Test
    public void testDeserializePaymentStatus() throws Exception {
        testObjectDeserialize(apiGson, "payment_status.json", PaymentStatus.class);
    }

    @Test
    public void testDeserializeEnrollmentNpi() throws Exception {
        testObjectDeserialize(apiGson, "enrollment_npis.json", EnrollmentNpiResponse.class);
    }

    @Test
    public void testDeserializeEnrollmentNpis() throws Exception {
        testObjectDeserialize(apiGson, "enrollment_npis_list.json", EnrollmentNpiQueryResponse.class);
    }

    @Test
    public void testDeserializeReceivedPdf() throws Exception {
        testObjectDeserialize(apiGson, "received_pdf.json", ReceivedPdfResponse.class);
    }

    @Test
    public void testDeserializeOriginalSignaturePdf() throws Exception {
        testObjectDeserialize(apiGson, "original_signature_pdf.json", OriginalSignaturePdfResponse.class);
    }

    @Test
    public void testDeserializeOriginalSignaturePdfDelete() throws Exception {
        testObjectDeserialize(apiGson, "delete_original_signature_pdf.json", OriginalSignaturePdfDeleteResponse.class);
    }

    public void testListDeserialize(Gson gson, String jsonResource, Type typeOfT) throws Exception {
        String json = resource(jsonResource);
        List source = gson.fromJson(json, List.class);

        Object sourceObj = gson.fromJson(json, typeOfT);
        List destination = constructRepresentationThroughGson(gson, sourceObj, List.class);

        assertEquals(source, destination, newLinkedList(newArrayList(typeOfT.toString())));
    }

    public <T extends EligibleObject> T testObjectDeserialize(Gson gson, String jsonResource, Class<T> typeOfT) throws Exception {
        String json = resource(jsonResource);
        Map source = gson.fromJson(json, Map.class);

        T sourceObj = gson.fromJson(json, typeOfT);
        Map destination = constructRepresentationThroughGson(gson, sourceObj, Map.class);

        assertEquals(source, destination, newLinkedList(newArrayList(typeOfT.toString())));
        assertEquals(sourceObj.getRawValues(), destination, newLinkedList(newArrayList(typeOfT.toString())));

        return sourceObj;
    }

    public static <T extends EligibleObject> void assertStructure(T sourceObj) {
        assertStructure(apiGson, sourceObj);
    }

    public static <T extends EligibleObject> void assertStructure(Gson gson, T sourceObj) {
        Map destination = constructRepresentationThroughGson(gson, sourceObj, Map.class);
        assertEquals(sourceObj.getRawValues(), destination, newLinkedList(newArrayList(sourceObj.getClass().toString())));
    }

    private static <T> T constructRepresentationThroughGson(Gson gson, Object sourceObj, Class<T> genericType) {
        String destinationJson = EligibleObject.PRETTY_PRINT_GSON.toJson(sourceObj);
        T destination = gson.fromJson(destinationJson, genericType);
        return destination;
    }


    public static void assertEquals(List source, List destination, LinkedList<String> keys) {
        if (source == null && destination == null) {
            return;
        }

        if (source == null || destination == null) {
            fail(keys + " node failed");
        }

        Assert.assertEquals(keys.toString(), source.size(), destination.size());

        for (int i = 0; i < source.size(); i++) {
            Object s = source.get(i);
            Object d = destination.get(i);

            keys.addLast(String.valueOf(i));
            if (s instanceof List && d instanceof List) {
                assertEquals((List) s, (List) d, keys);
            } else if (s instanceof Map && d instanceof Map) {
                assertEquals((Map) s, (Map) d, keys);
            } else {
                Assert.assertEquals(keys.toString(), s, d);
            }

            keys.removeLast();
        }
    }

    public static void assertEquals(Map<String, ?> source, Map<String, ?> destination, LinkedList<String> keys) {
        if (source == null && destination == null) {
            return;
        }

        if (source == null || destination == null) {
            fail(keys + " node failed. Source:" + source + ", Destination: " + destination);
        }

//        Assert.assertEquals(
//                String.format("At %s, Expected keys: %s, Got keys: %s", keys, source.keySet(), destination.keySet()),
//                source.size(), destination.size());

        for (String key : source.keySet()) {
            Object s = source.get(key);
            Object d = destination.get(key);

            keys.addLast(key);
            if (s instanceof List && d instanceof List) {
                assertEquals((List) s, (List) d, keys);
            } else if (s instanceof Map && d instanceof Map) {
                assertEquals((Map) s, (Map) d, keys);
            } else {
                Assert.assertEquals(keys.toString(), s, d);
            }

            keys.removeLast();
        }
    }
}
