package com.inspur.dsp.open.sync.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private JsonUtil(){}

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static String convertToString(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T readToObject(String str, Class<T> cla) throws IOException {
        return objectMapper.readValue(str, cla);
    }

    public static <T> T obj2pojo(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

}
