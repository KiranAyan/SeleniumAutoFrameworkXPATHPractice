package com.practice.utils;
import java.io.File;
import java.util.List;
import com.practice.pojo.LoginData;

import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    public static List<LoginData> getLoginData(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), new TypeReference<List<LoginData>>() {});
    }
}