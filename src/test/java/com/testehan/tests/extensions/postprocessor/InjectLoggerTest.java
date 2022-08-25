package com.testehan.tests.extensions.postprocessor;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(LoggingExtension.class)
public class InjectLoggerTest {
    private Logger logger;

    @Test
    public void justLogSomething(){
        logger.info("I am a log from a test");
    }

    @Test
    public void justLogSomethingAgain(){
        logger.info("I am another log from a test");
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
