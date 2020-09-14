package com.epam.ta.tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.AbstractIntegerAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseAssert {

    public static AbstractIntegerAssert<?> assertStatusOk(Response response) {
        return assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_OK);
    }

    public static AbstractIntegerAssert<?> assertStatusCreated(Response response) {
        return assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_CREATED);
    }

}
