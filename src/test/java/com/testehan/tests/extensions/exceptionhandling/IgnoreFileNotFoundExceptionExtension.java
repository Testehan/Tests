package com.testehan.tests.extensions.exceptionhandling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.io.FileNotFoundException;

public class IgnoreFileNotFoundExceptionExtension implements TestExecutionExceptionHandler {

    Logger logger = LogManager.getLogger(IgnoreFileNotFoundExceptionExtension.class);

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof FileNotFoundException) {
            logger.error("File not found:" + throwable.getMessage());
            return;
        }
        if (throwable instanceof IllegalArgumentException) {
            return ; // no message in the log for this
        }
        throw throwable;
    }
}
