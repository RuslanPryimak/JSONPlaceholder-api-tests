package com.epam.ta.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseSteps {

    @Step("HTTP Status is 200")
    public void assertStatusOk(Response response) {
        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_OK);
    }

    @Step("HTTP Status is 201")
    public void assertStatusCreated(Response response) {
        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_CREATED);
    }

    @Step("HTTP Status is 404")
    public void assertStatusNotFound(Response response) {
        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_NOT_FOUND);
    }
}
