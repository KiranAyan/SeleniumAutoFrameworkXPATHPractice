package com.practice.utils;

import java.util.List; // Missing Import 1
import org.testng.annotations.DataProvider;
import com.practice.pojo.LoginData; // Missing Import 2 (Your POJO path)

public class JsonDataProvider {

    @DataProvider(name = "getJsonLoginData")
    public Object[][] getData() throws Exception {
        // 1. Path to your JSON file in resources
        String filePath = "src\\test\\resources\\testdata\\TestData.json";
        
        // 2. Use your Utility to read the JSON into a List of POJOs
        List<LoginData> dataList = JsonUtils.getLoginData(filePath);
        
        // 3. Convert List to Object[][] (TestNG requirement)
        Object[][] data = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }
}