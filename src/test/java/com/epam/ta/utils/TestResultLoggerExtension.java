package com.epam.ta.utils;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestResultLoggerExtension implements TestWatcher, AfterAllCallback {
    private static final Logger LOG = LoggerFactory.getLogger(TestResultLoggerExtension.class);

    private List<TestResultStatus> testResultsStatus = new ArrayList<>();

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED

    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        LOG.info("Status 'DISABLED' for test '{}' with reason: '{}'",
                context.getDisplayName(),
                reason.orElse("No reason"));

        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOG.info("Status 'SUCCESSFUL' for test '{}'",
                context.getDisplayName());

        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LOG.info("Status 'ABORTED' for test '{}'",
                context.getDisplayName());

        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOG.info("Status 'FAILED' for test '{}'",
                context.getDisplayName());

        testResultsStatus.add(TestResultStatus.FAILED);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        LOG.info("TEST RESULT SUMMARY FOR '{}' {}", context.getDisplayName(), summary.toString());
    }
}