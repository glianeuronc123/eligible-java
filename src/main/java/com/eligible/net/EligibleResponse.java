package com.eligible.net;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class EligibleResponse {

    private int responseCode;
    private String responseBody;
    private Map<String, List<String>> responseHeaders;

    public EligibleResponse(int responseCode, String responseBody) {
        this(responseCode, responseBody, null);
    }
}
