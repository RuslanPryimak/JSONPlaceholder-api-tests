package com.epam.ta.configuration;

import org.aeonbits.owner.ConfigFactory;

public class TestConfiguration {

    public TestProperties testProperties() {
        return ConfigFactory.create(TestProperties.class);
    }

    public String baseUrl() {
        return testProperties().getBaseUrl();
    }
}
