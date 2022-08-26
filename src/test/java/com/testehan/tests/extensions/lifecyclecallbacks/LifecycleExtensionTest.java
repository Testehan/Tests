package com.testehan.tests.extensions.lifecyclecallbacks;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;


// TODO when you learn about h2 database you can do the extension with it : https://www.baeldung.com/junit-5-extensions

@ExtendWith(LifecycleExtention.class)
public class LifecycleExtensionTest {

    @BeforeAll
    public static void setup() {
        System.out.println("2. @BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    public void init() {
        System.out.println("4. @BeforeEach - executes before each test method in this class");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("6. @AfterEach - executed after each test method.");
    }

    @AfterAll
    public static void done() {
        System.out.println("8. @AfterAll - executed after all test methods.");
    }

    @Test
    public void test(){
        System.out.println("5. Inside the test");
    }
}
