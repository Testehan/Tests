package com.testehan.tests.extensions.exceptionhandling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileNotFoundException;

@ExtendWith(IgnoreFileNotFoundExceptionExtension.class)
public class ExceptionHandlingTest {

    @Test
    public void fileNotFoundIsThrown_logMessageShouldAppear() throws FileNotFoundException {
        throw new FileNotFoundException("I hope you see this in the log");
    }

    @Test
    public void illegalArgumentIsThrown_messageShouldNotBeInLog(){
        throw new IllegalArgumentException("This should not be in the log");
    }
}
