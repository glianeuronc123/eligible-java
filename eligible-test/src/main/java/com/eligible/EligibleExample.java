package com.eligible;

import com.eligible.model.Coverage;
import com.eligible.model.coverage.Plan;
import com.eligible.net.RequestOptions;
import com.eligible.net.RequestOptions.RequestOptionsBuilder;

import java.util.HashMap;
import java.util.Map;

public class EligibleExample extends EligibleMainBase {

    public static void main(String[] args) throws Exception {
        RequestOptions requestOptions = new RequestOptionsBuilder()
                .setApiKey(API_KEY)
                .setTest(true)
                .build();

        Map<String, Object> allParams = new HashMap<String, Object>();
        allParams.put("payer_id", "00001");
        allParams.put("provider_last_name", "Doe");
        allParams.put("provider_first_name", "John");
        allParams.put("provider_npi", "0123456789");
        allParams.put("member_id", "ZZZ445554301");
        allParams.put("member_first_name", "IDA");
        allParams.put("member_last_name", "FRANKLIN");
        allParams.put("member_dob", "1701-12-12");
        allParams.put("service_type", "30");

        Coverage coverage = Coverage.all(allParams, requestOptions);
        System.out.println(coverage);
        System.out.println(coverage.dump());            // dump contains raw parameters.

        Map<String, ?> planMap = coverage.get("plan");  // get method returns raw parameters
        System.out.println(planMap);
        System.out.println(planMap.get("dates"));

        Plan planObject = coverage.getPlan();           // get<> methods return Object representation
        System.out.println(planObject);
        System.out.println(planObject.dump());
        System.out.println(planObject.get("dates"));
        System.out.println(planObject.getDates());      // OK, you get the idea now :D
    }
}
