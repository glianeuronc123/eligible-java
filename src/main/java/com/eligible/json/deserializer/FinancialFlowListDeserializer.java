package com.eligible.json.deserializer;

import com.eligible.model.coverage.FinancialFlow;
import com.eligible.net.APIResource;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link JsonDeserializer} for deserializing {@link FinancialFlow}.
 */
public class FinancialFlowListDeserializer implements JsonDeserializer<List<FinancialFlow>> {
    private static final Type DATE_LIST_TYPE = new TypeToken<List<FinancialFlow>>() { }.getType();

    /** {@inheritDoc} */
    public List<FinancialFlow> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        final Gson gson = APIResource.GSON;

        // List could be a list of FinancialFlow.class, or a list of list of FinancialFlow.class. Merging all into one
        List<FinancialFlow> collection = new ArrayList<FinancialFlow>();
        JsonArray jsonFinancialFlows = json.getAsJsonArray();

        for (JsonElement jsonFinancialFlow : jsonFinancialFlows) {
            if (jsonFinancialFlow.isJsonArray()) {
                List<FinancialFlow> dates = gson.fromJson(jsonFinancialFlow, DATE_LIST_TYPE);
                collection.addAll(dates);
            } else {
                FinancialFlow date = gson.fromJson(jsonFinancialFlow, FinancialFlow.class);
                collection.add(date);
            }
        }
        return collection;
    }
}
