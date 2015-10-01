package com.eligible.net;

import lombok.Cleanup;

import java.io.*;
import java.net.URLConnection;

/**
 * Implementation for making HTTP multipart requests.
 */
public class MultipartProcessor {
    private final String boundary;
    private static final String LINE_BREAK = "\r\n";
    private static final int OUTPUT_BUFFER_SIZE = 4096;
    private OutputStream outputStream;
    private PrintWriter writer;
    private String charset;
    private java.net.HttpURLConnection conn;

    /**
     * Create a HTTP Multipart Processor.
     *
     * @param conn
     * @param boundary
     * @param charset
     * @throws IOException
     */
    public MultipartProcessor(java.net.HttpURLConnection conn, String boundary, String charset)
            throws IOException {
        this.boundary = boundary;
        this.charset = charset;
        this.conn = conn;

        this.outputStream = conn.getOutputStream();
        this.writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
    }

    /**
     * Add for data into request.
     *
     * @param name
     * @param value
     */
    public void addFormField(String name, String value) {
        writer.append("--" + boundary).append(LINE_BREAK);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                .append(LINE_BREAK);
        writer.append(LINE_BREAK);
        writer.append(value).append(LINE_BREAK);
        writer.flush();
    }

    /**
     * Add file to be uploading into request.
     *
     * @param name
     * @param file
     * @throws IOException
     */
    public void addFileField(String name, File file) throws IOException {
        String fileName = file.getName();
        writer.append("--" + boundary).append(LINE_BREAK);
        writer.append(
                "Content-Disposition: form-data; name=\"" + name
                        + "\"; filename=\"" + fileName + "\"").append(
                LINE_BREAK);

        String probableContentType = URLConnection
                .guessContentTypeFromName(fileName);
        writer.append("Content-Type: " + probableContentType)
                .append(LINE_BREAK);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_BREAK);
        writer.append(LINE_BREAK);
        writer.flush();

        @Cleanup FileInputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[OUTPUT_BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();

        writer.append(LINE_BREAK);
        writer.flush();
    }

    /**
     * Flush streams and cleanup.
     * @throws IOException
     */
    public void finish() throws IOException {
        writer.append("--" + boundary + "--").append(LINE_BREAK);
        writer.flush();
        writer.close();
        outputStream.flush();
        outputStream.close();
    }

}
