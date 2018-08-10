package com.packt.rhuan.utils;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipInputStream;

public class FileSystemUtils {

    public static String save ( File file, String path, String fileName ) throws IOException {

        new File(System.getProperty("java.io.tmpdir")+"/" +path).mkdirs();
        File fileToSave = new File(System.getProperty("java.io.tmpdir")+"/" +path + "/" + fileName);

        try (InputStream input = new FileInputStream( file )) {

            Files.copy( input, fileToSave.toPath() );

        }

        return fileToSave.getAbsolutePath();

    }


}
