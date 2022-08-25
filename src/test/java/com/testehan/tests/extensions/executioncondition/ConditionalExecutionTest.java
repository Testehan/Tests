package com.testehan.tests.extensions.executioncondition;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EnvironmentExtension.class)
@Tag("current")
public class ConditionalExecutionTest {

    @Test
    public void thisShouldRunIfEnvIsNotQA(){
        System.out.println("This should run");
    }
}
