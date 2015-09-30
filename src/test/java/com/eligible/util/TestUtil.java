package com.eligible.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class TestUtil {

    public static String resource(String path, Class clazz) throws IOException {
        InputStream resource = clazz.getResourceAsStream(path);

        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        byte[] buf = new byte[1024];

        for (int i = resource.read(buf); i > 0; i = resource.read(buf)) {
            os.write(buf, 0, i);
        }

        return os.toString("utf8");

    }
}
