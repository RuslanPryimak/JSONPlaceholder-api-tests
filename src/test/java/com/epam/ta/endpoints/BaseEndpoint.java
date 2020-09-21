package com.epam.ta.endpoints;

import com.epam.ta.configuration.TestConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseEndpoint {

    private TestConfiguration testConfiguration = new TestConfiguration();

    private final String BASE_URL = testConfiguration.baseUrl();

    public RequestSpecification given() {
        return RestAssured.given().baseUri(BASE_URL).port(-1).log().all().contentType(ContentType.JSON);
    }

}
