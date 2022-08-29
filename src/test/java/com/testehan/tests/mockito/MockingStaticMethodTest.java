package com.testehan.tests.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MockingStaticMethodTest {

    @Test
    void shouldMockStaticMethod() {
        try (MockedStatic<Utility> mockedStatic = Mockito.mockStatic(Utility.class)) {

            mockedStatic.when(() -> Utility.getDatabaseConnection(Mockito.eq("test"))).thenReturn("testing");
            mockedStatic.when(() -> Utility.getDatabaseConnection(Mockito.eq("prod"))).thenReturn("production");

            String result1 = Utility.getDatabaseConnection("test");
            assertEquals("testing", result1);

            String result2 = Utility.getDatabaseConnection("prod");
            assertEquals("production", result2);

        }

    }
}
