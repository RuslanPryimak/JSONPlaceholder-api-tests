package com.epam.ta.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class BaseIT {

    @BeforeEach
    public void printTestName(TestInfo testInfo) {
        System.out.println("Test case: " + testInfo.getDisplayName());
    }

}
