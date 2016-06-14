package com.eligible;

import com.google.common.base.Joiner;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.String.format;

/** Test for validating complete updation of {@link Eligible#VERSION}. */
public class DocumentationTest {

    private BufferedReader getBufferedReader(String fileName) throws IOException {
        File versionFile = new File(fileName).getAbsoluteFile();
        Assert.assertTrue(format("Expected %s file to exist, but it doesn't. (path is %s).", fileName, versionFile.getAbsolutePath()), versionFile.exists());
        Assert.assertTrue(format("Expected %s to be a file, but it isn't. (path is %s).", fileName, versionFile.getAbsolutePath()), versionFile.isFile());
        return new BufferedReader(new FileReader(versionFile));
    }

    @Test
    public void testVersionAgreesWithVERSIONFile() throws IOException {
        BufferedReader reader = getBufferedReader("VERSION");
        String firstLine = reader.readLine();
        Assert.assertEquals(firstLine, Eligible.VERSION);
    }

    private static String formatDateTime() {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = format.format(instance.getTime());
        String result = "=== " + Eligible.VERSION + " " + formattedDate;
        return result;
    }

    @Test
    public void testChangeLogContainsStaticVersion() throws IOException {
        BufferedReader reader = getBufferedReader("CHANGELOG");
        String expectedLine = formatDateTime();
        String line;
        List<String> closeMatches = new LinkedList<String>();
        while ((line = reader.readLine()) != null) {
            if (line.contains(Eligible.VERSION)) {
                if (Pattern.matches(format("^=== %s 20[12][0-9]-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])$", Eligible.VERSION), line)) {
                    return;
                }
                closeMatches.add(line);
            }
        }
        Assert.fail(format("Expected a line of the format '%s' in the CHANGELOG, but didn't find one.%nThe following lines were close, but didn't match exactly:%n'%s'", expectedLine, Joiner.on(", ").join(closeMatches)));
    }

    @Test
    public void testReadMeContainsMavenPomThatMatches() throws IOException {
        // this will be very flaky, but we want to ensure that the readme is correct.
        BufferedReader reader = getBufferedReader("README.md");
        int expectedMentionsOfVersion = 2;
        // Currently two places mention the Eligible version: the sample pom and gradle files.
        List<String> mentioningLines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(Eligible.VERSION)) {
                mentioningLines.add(line);
            }
        }
        String message = format("Expected %d mentions of the eligible-java version in the Readme, but found %d:%n%s", expectedMentionsOfVersion, mentioningLines.size(), Joiner.on(", ").join(mentioningLines));
        Assert.assertSame(message, expectedMentionsOfVersion, mentioningLines.size());
    }

}
