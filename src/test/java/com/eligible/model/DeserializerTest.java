package com.eligible.model;

import com.eligible.BaseEligibleTest;
import com.eligible.net.APIResource;
import com.google.gson.Gson;
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

    private static Gson gson = APIResource.GSON;

    @Test
    public void testDeserializeDates() throws Exception {
        gson.fromJson(resource("dates.json"), Dates.class);     // should not throw error
    }

    @Test
    public void testDeserializePayerList() throws Exception {
        Type listPayerType = new TypeToken<List<Payer>>() {
        }.getType();
        testListDeserialize("payers.json", listPayerType);
    }

    @Test
    public void testDeserializePayer() throws Exception {
        testObjectDeserialize("payer.json", Payer.class);
    }

    @Test
    public void testDeserializeSearchOptionsList() throws Exception {
        Type listSearchOptionsType = new TypeToken<List<Payer.SearchOptions>>() {
        }.getType();
        testListDeserialize("search_options_list.json", listSearchOptionsType);
    }

    @Test
    public void testDeserializeSearchOptions() throws Exception {
        testObjectDeserialize("search_options.json", Payer.SearchOptions.class);
    }

    @Test
    public void testDeserializeCoverage() throws Exception {
        testObjectDeserialize("coverage.json", Coverage.class);
    }

    @Test
    public void testDeserializeMedicareCoverage() throws Exception {
        testObjectDeserialize("medicare_coverage.json", Coverage.Medicare.class);
    }

    public <T> void testListDeserialize(String jsonResource, Type typeOfT) throws Exception {
        String json = resource(jsonResource);
        List source = gson.fromJson(json, List.class);

        Object sourceObj = gson.fromJson(json, typeOfT);
        String destinationJson = EligibleObject.PRETTY_PRINT_GSON.toJson(sourceObj);
        List destination = gson.fromJson(destinationJson, List.class);

        assertEquals(source, destination, newLinkedList(newArrayList(typeOfT.toString())));
    }

    public void testObjectDeserialize(String jsonResource, Type typeOfT) throws Exception {
        String json = resource(jsonResource);
        Map source = gson.fromJson(json, Map.class);

        Object sourceObj = gson.fromJson(json, typeOfT);
        String destinationJson = EligibleObject.PRETTY_PRINT_GSON.toJson(sourceObj);
        Map destination = gson.fromJson(destinationJson, Map.class);

        assertEquals(source, destination, newLinkedList(newArrayList(typeOfT.toString())));
    }


    public static void assertEquals(List source, List destination, LinkedList<String> keys) {
        if (source == null && destination == null)
            return;

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
        if (source == null && destination == null)
            return;

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
