package com.freshcells;

import java.util.List;

import com.freshcells.utils.FileUtils;
import com.freshcells.utils.JsonUtils;
import com.freshcells.utils.XmlUtils;

public class XmlToJsonConverter {

    private static final String HELP = "-h help\n" +
            "-p <path> path to list of xml files \n" +
            "-r search recursively for files in folders \n" +
            "-o the path where you want to save the file and the file name";

    public static void main(String[] args) {

        String pathToXml = "./";
        boolean recursive = false;
        String outPath = "./convertedJson.json";

        if (args.length > 0) {
            if (args.length == 1 && args[0].equals("-h")) {
                System.out.println(HELP);
                return;
            }
            for (int i = 0; i < args.length - 1; i++) {
                String argument = args[i];
                String argValue = args[i + 1];
                try {
                    switch (argument) {
                        case("-h"):
                            System.out.println(HELP);
                            return;
                        case("-p"):
                            pathToXml = String.valueOf(argValue);
                            break;
                        case("-r"):
                            recursive = Boolean.parseBoolean(argValue);
                            break;
                        case("-o"):
                            outPath = String.valueOf(argValue);
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Wrong arguments, use:");
                    System.out.println(HELP);
                }
            }
        }
        System.out.println("XmlToJsonConverter was started!");

        List<String> xmlList = FileUtils.findFiles(pathToXml, recursive, "xml");
        List<String> jsonList = XmlUtils.toListJson(xmlList);
        String json = JsonUtils.mergeListJson(jsonList);
        FileUtils.writeFile(json, outPath);

        System.out.println("XmlToJsonConverter was finished!");
    }
}
