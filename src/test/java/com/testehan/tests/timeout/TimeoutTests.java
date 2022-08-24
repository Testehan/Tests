package com.testehan.tests.timeout;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

//@Timeout(7) // each test fails if execution time exceeds 7 seconds
@Tag("slow")
public class TimeoutTests {

    @BeforeAll
    @Timeout(4) // skip all tests if this method execution time exceeds 4 seconds
    static void beforeAll() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    @Timeout(4) // fail all tests if this method execution time exceeds 4 seconds
    void beforeEach() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("@BeforeEach");
    }


    @Test
    @Timeout(9) // this test fails if execution time exceeds 9 seconds
    void test_Add() throws Exception {

        TimeUnit.SECONDS.sleep(10); // waits 10 seconds
        System.out.println("test_Add()");
    }

    @Test
    @Timeout(9) // this test fails if execution time exceeds 9 seconds
    void test_Multiply() throws Exception {

        TimeUnit.SECONDS.sleep(8); // waits 8 seconds
        System.out.println("test_Multiply()");
    }

    @Test
    @Timeout(value = 900, unit = TimeUnit.MILLISECONDS)
    void test_isPrime() throws Exception {

        // fails if execution time exceeds 900 milli seconds
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("test_isPrime()");
    }

    @Test
    void test_Divide() throws Exception {

        // fails but completes the process, see output and run duration
        assertTimeout(Duration.ofSeconds(5), () -> {
            TimeUnit.SECONDS.sleep(20);
            assertEquals(5, 10/2);
            System.out.println("test_Divide()");
        });
    }

    @Test
    void test_assertTimeoutPreemptively() throws Exception {

        // fails but abort the process, see output and run duration
        // In case we want to be sure that execution of the executable will be aborted once it exceeds the timeout,
        // we can use the assertTimeoutPreemptively assertion.
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            TimeUnit.SECONDS.sleep(20);
            assertEquals(5, 3+ 2);
            System.out.println("test_assertTimeoutPreemptively()");
        });
    }
}
