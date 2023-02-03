package com.freshcells.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtils {

    public static void writeFile(String json, String outPath) {
        Path file = Paths.get(outPath);
        try {
            Files.write(file, Collections.singletonList(json), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Unable to write output file with final json: " + e.toString());
        }
    }

    public static List<String> findFiles(String path, Boolean recursive, String type) {
        File folder = new File(path);

        return readFiles(folder, recursive, type);
    }

    private static List<String> readFiles(File file, Boolean recursive, String type) {
        List<String> xmlList = new ArrayList<>();
        if (file.listFiles() == null) {
            return xmlList;
        }

        for (File fileEntry : file.listFiles()) {
            if (fileEntry.isFile() && getExtension(fileEntry.getName()).equals(type)) {
                String xml = "";

                try {
                    xml = readFile(fileEntry.getPath());
                } catch (IOException e) {
                    System.out.println("Unable to open file: '" + fileEntry.getAbsolutePath() + "'");
                }

                if (!xml.isBlank()) {
                    xmlList.add(xml);
                }
            } else if (fileEntry.isDirectory() && recursive) {
                readFiles(fileEntry, true, type);
            }
        }
        return xmlList;
    }

    private static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    private static String getExtension(String filename) {
        return filename.contains(".") ? filename.substring(filename.lastIndexOf(".") + 1) : "";
    }
}
