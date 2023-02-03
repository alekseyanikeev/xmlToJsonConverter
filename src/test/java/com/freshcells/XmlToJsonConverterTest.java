package com.freshcells;

import com.freshcells.utils.FileUtils;
import com.freshcells.utils.JsonUtils;
import com.freshcells.utils.XmlUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class XmlToJsonConverterTest {
    @Test
    public void testMain() {
        String testSampleJsonString = "";
        try {
            testSampleJsonString = Files.readString(Paths.get("src/test/resources/convertedJson.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> xmlList = FileUtils.findFiles("src/test/resources/", false, "xml");
        List<String> jsonList = XmlUtils.toListJson(xmlList);
        String resultJsonString = JsonUtils.mergeListJson(jsonList);

        assertEquals(resultJsonString, testSampleJsonString.substring(0, testSampleJsonString.length() - 1)); // bad way to substring =)
    }
}
