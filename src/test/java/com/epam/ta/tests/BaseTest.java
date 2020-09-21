package com.epam.ta.tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    public void assertStatusOk(Response response) {
        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_OK);
    }

    public void assertStatusCreated(Response response) {
        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_CREATED);
    }

}
