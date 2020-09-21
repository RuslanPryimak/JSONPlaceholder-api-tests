package com.epam.ta.endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseEndpoint {

    static final String BASE_URL = "http://jsonplaceholder.typicode.com";

    public static RequestSpecification given() {
        return RestAssured.given().baseUri(BASE_URL).port(-1).log().all().contentType(ContentType.JSON);
    }

}
