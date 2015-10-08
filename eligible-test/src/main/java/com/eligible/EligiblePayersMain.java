package com.eligible;


import com.eligible.model.Payer;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.List;

public class EligiblePayersMain extends EligibleMainBase {

    public static void main(String[] args) throws Exception {

//        testPayersAll();
//        testPayerRetrieve();

//        testSearchOptionsAll();
//        testSearchOptionsRetrieve();

    }

    static void testPayersAll() throws Exception {
//        List<Payer> payers = Payer.all();
        List<Payer> payers = readPayersFile();

        System.out.println(payers.size());
        for(Payer payer : payers) {
            System.out.println(payer.dump());
        }
    }

    static void testPayerRetrieve() throws Exception {
        Payer payer = Payer.retrieve("WYMCR");

        System.out.println(payer);
        System.out.println(payer.dump());
    }

    static List<Payer> readPayersFile() throws FileNotFoundException {
        String fileName = "./src/test/resources/com/eligible/model/payers.json";
        Type type = new TypeToken<List<Payer>>(){}.getType();

        return parseResource(fileName, type);
    }

    static void testSearchOptionsAll() throws Exception {
        List<Payer.SearchOptions> searchOptions = Payer.searchOptions();
        for(Payer.SearchOptions payerSearchOption : searchOptions) {
            System.out.println(payerSearchOption.dump());
        }
    }


    static void testSearchOptionsRetrieve() throws Exception {
        Payer.SearchOptions searchOptions = Payer.searchOptions("WYMCR");
        System.out.println(searchOptions.dump());
    }
}
