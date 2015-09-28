package com.eligible.net;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class EligibleResponse {

    int responseCode;
    String responseBody;
    Map<String, List<String>> responseHeaders;

    public EligibleResponse(int responseCode, String responseBody) {
        this(responseCode, responseBody, null);
    }
}
