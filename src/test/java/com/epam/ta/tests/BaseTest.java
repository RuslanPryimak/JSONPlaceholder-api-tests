package com.epam.ta.tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    @BeforeEach
    public void printTestName(TestInfo testInfo) {
        System.out.println("Test case: " + testInfo.getDisplayName());
    }

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

    public void assertStatusNotFound(Response response) {
        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

}
