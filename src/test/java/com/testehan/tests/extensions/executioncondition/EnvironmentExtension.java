package com.testehan.tests.extensions.executioncondition;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentExtension implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {

        Properties props = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/application.properties")) {
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        final String env = props.getProperty("env");
        if ("qa".equalsIgnoreCase(env)) {
            return ConditionEvaluationResult.disabled("Test disabled on QA environment");
        } else {
            return ConditionEvaluationResult.enabled("Test enabled on QA environment");
        }
    }
}
