package com.eligible;

import com.eligible.model.PaymentStatus;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class EligiblePaymentStatusMain extends EligibleMainBase {
    static final Map<String, Object> queryParams = new HashMap<String, Object>();

    public static void main(String[] args) throws Exception {
        testPaymentStatus();
    }

    static void testPaymentStatus() throws Exception {
        queryParams.put("payer_id", "00001");
        queryParams.put("provider_npi", "0123456789");
        queryParams.put("provider_tax_id", "111223333");
        queryParams.put("member_id", "ZZZ445554301");
        queryParams.put("member_first_name", "IDA");
        queryParams.put("member_last_name", "FRANKLIN");
        queryParams.put("member_dob", "1701-12-12");
        queryParams.put("payer_control_number", "123123123");
        queryParams.put("charge_amount", "125.00");
        queryParams.put("start_date", "2010-06-15");
        queryParams.put("end_date", "2010-06-15");
        queryParams.put("trace_number", "BHUYTOK98IK");

        PaymentStatus paymentStatus = readPaymentStatusFile();
//        PaymentStatus paymentStatus = PaymentStatus.retrieve(queryParams);
        System.out.println(paymentStatus);
    }

    static PaymentStatus readPaymentStatusFile() throws FileNotFoundException {
        String fileName = "./src/test/resources/com/eligible/model/payment_status.json";
        return parseResource(fileName, PaymentStatus.class);
    }
}
