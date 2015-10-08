package com.eligible.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utility methods for Testing.
 */
public abstract class TestUtil {

    /**
     * Read the resource file.
     *
     * @param path file name
     * @param clazz package location of file
     * @return file content
     * @throws IOException
     */
    public static String resource(String path, Class clazz) throws IOException {
        BufferedReader resource = new BufferedReader(new InputStreamReader(clazz.getResourceAsStream(path)));

        StringBuilder sb = new StringBuilder();
        for (String s = resource.readLine(); s != null; s = resource.readLine()) {
            sb.append(s);
        }

        return sb.toString();
    }
}
