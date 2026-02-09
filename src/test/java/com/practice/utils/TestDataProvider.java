package com.practice.utils;
import org.testng.annotations.DataProvider;
import java.io.IOException;

public class TestDataProvider {

    @DataProvider(name = "formData")
    public Object[][] getFormData() throws IOException {
        String path = "src\\test\\resources\\testdata\\TestData.csv";
        return DataUtils.readCsvData(path);
        
    }
    
    // You can add more providers here, like "loginData", "productData", etc.
}