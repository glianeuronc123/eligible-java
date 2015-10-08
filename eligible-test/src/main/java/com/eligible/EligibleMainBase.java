package com.eligible;

import com.eligible.net.APIResource;
import com.eligible.util.NetworkUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.Scanner;

public class EligibleMainBase {
    static final String API_KEY = "n5Cddnj2KST6YV9J2l2ztQQ2VrdPfzA4JPbn";

    static {
        Eligible.apiKey = API_KEY;
        Eligible.isTest = true;
    }

    public static String readFile(String fileName) throws FileNotFoundException {
        return new Scanner(new FileInputStream(new File(fileName)), NetworkUtil.CHARSET)
                .useDelimiter("\\A")
                .next();
    }

    public static <T> T parseResource(String fileName, Type typeOfT) throws FileNotFoundException {
        String json = readFile(fileName);

        return APIResource.GSON.fromJson(json, typeOfT);
    }

    public static <T> T parseResource(String fileName, Class<T> typeOfT) throws FileNotFoundException {
        return parseResource(fileName, (Type) typeOfT);
    }
}
