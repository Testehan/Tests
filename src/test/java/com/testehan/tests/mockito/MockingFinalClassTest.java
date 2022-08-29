package com.testehan.tests.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MockingFinalClassTest {

    @Test
    public void testMockFinal(@Mock RandomFinalClass finalMocked)  {
        assertNotNull(finalMocked);
    }

    @Test
    public void testMockFinalViaMockStatic()  {
        MockedStatic<RandomFinalClass> mockStatic = Mockito.mockStatic(RandomFinalClass.class);
        assertNotNull(mockStatic);
    }
}
