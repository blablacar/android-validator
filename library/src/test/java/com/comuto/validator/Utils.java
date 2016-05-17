package com.comuto.validator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import junit.framework.Assert;
import org.junit.rules.TemporaryFolder;

public class Utils {
    public static File getFileFromResource(Object obj, String resourceName) throws IOException {
        TemporaryFolder folder = new TemporaryFolder();
        folder.create();
        File file = folder.newFile(resourceName);

        InputStream stream = obj.getClass().getClassLoader().getResourceAsStream(resourceName);

        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);

        OutputStream outStream = new FileOutputStream(file);
        outStream.write(buffer);

        return file;
    }

    public static void assertEmpty(String message, Iterable<?> iterable) {
        if (iterable.iterator().hasNext()) {
            failNotEmpty(message, iterable.toString());
        }
    }

    public static void assertNotEmpty(String message, Iterable<?> iterable) {
        if (!iterable.iterator().hasNext()) {
            failEmpty(message);
        }
    }

    private static void failNotEmpty(
        String message, String actual) {
        failWithMessage(message, "expected to be empty, but contained: <"
            + actual + ">");
    }

    private static void failEmpty(String message) {
        failWithMessage(message, "expected not to be empty, but was");
    }

    private static void failWithMessage(String userMessage, String ourMessage) {
        Assert.fail((userMessage == null)
            ? ourMessage
            : userMessage + ' ' + ourMessage);
    }

}
