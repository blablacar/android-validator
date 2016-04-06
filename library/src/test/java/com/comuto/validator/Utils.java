package com.comuto.validator;

import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

}
