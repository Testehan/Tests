package com.testehan.tests.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    Database databaseMock;

    @InjectMocks
    private Service serviceWithInjectedMocks;

    @Test
    public void testQuery() {
        // given
        assertNotNull(databaseMock);
        when(databaseMock.isAvailable()).thenReturn(true);
        Service service = new Service(databaseMock);

        // when
        boolean check = service.query("* from service");

        // then
        assertTrue(check);
    }

    @Test
    void ensureMockitoReturnsTheConfiguredValue() {

        // define return value for method getUniqueId()
        when(databaseMock.getUniqueId()).thenReturn(42).thenReturn(54);

        Service service = new Service(databaseMock);

        assertEquals(service.toString(), "Using database with id: 42");
        assertEquals(service.toString(), "Using database with id: 54");
    }

    @Test
    void testMockitoThrows() {
        Properties properties = mock(Properties.class);

        when(properties.get(anyString())).thenThrow(new IllegalArgumentException("Stuff"));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> properties.get("A"));

        assertEquals("Stuff", exception.getMessage());
    }

    @Test
    public void testObjectCreatedWithInjection() {
        // given
        when(databaseMock.isAvailable()).thenReturn(false);

        // when
        boolean queryIsAvaiable = serviceWithInjectedMocks.query("abc");

        // then
        assertEquals(false, queryIsAvaiable);
    }

    @Test
    void givenVoidMethod_callingDoThrow_shouldConfigureBehavior() {
        // given
        doThrow(new IllegalArgumentException()).when(databaseMock).setUniqueId(anyInt());

        // when
        Executable workCall = () -> databaseMock.setUniqueId(1);

        // then
        assertThrows(IllegalArgumentException.class, workCall);
    }
}
