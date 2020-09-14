package com.epam.ta.utils;

import com.google.gson.Gson;
import io.restassured.response.Response;

import java.lang.reflect.Type;

public class JsonConverter {

    private static Gson GSON = new Gson();

    public static <T> T convertToJson(Response response, Class<T> clazz) {
        return GSON.fromJson(response.jsonPath().prettify(), (Type) clazz);
    }
}