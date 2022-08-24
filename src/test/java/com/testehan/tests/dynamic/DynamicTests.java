package com.testehan.tests.dynamic;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class DynamicTests {

    @TestFactory
    DynamicTest singleDynamicTest() {
        return dynamicTest("Single dynamic test",
                () -> assertTrue(13>0));
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {

        return Arrays.asList(
                dynamicTest("1st dynamic test", () -> assertTrue(13>0)),
                dynamicTest("2nd dynamic test", () -> assertEquals(5, 25/5)));
    }



    // This method produces Dynamic test cases
    @TestFactory
    Stream<DynamicNode> dynamicTestsFromStreamOfNumbers() {
        return Stream.of(7, 13)
                .map(number -> DynamicContainer.dynamicContainer("Greater than 2 and Odd Test for "+number,
                        Stream.of(
                                DynamicTest.dynamicTest("is number "+number+" greater than 2?",
                                        () -> assertTrue(number > 2)),
                                DynamicTest.dynamicTest("is number "+number+" odd?",
                                        () -> assertFalse(number % 2 ==0)
                                ))));
    }

    @Execution(ExecutionMode.CONCURRENT)
    @TestFactory
    Collection<DynamicTest> concurrentDynamicTests() {

        return Arrays.asList(
                dynamicTest("1st dynamic test", () -> {
                    assertTrue(13 >2);
                    System.out.println(Thread.currentThread().getName()+" => 1st dynamic test");
                }),
                dynamicTest("2nd dynamic test", () -> {
                    assertEquals(5, 25/ 5);
                    System.out.println(Thread.currentThread().getName()+" => 2nd dynamic test");
                }),
                dynamicTest("3rd dynamic test", () -> {
                    assertEquals(12, 7+ 5);
                    System.out.println(Thread.currentThread().getName()+" => 3rd dynamic test");
                })
        );
    }

    @Execution(ExecutionMode.SAME_THREAD)
    @TestFactory
    Collection<DynamicTest> sameThreadDynamicTests() {

        return Arrays.asList(
                dynamicTest("4th dynamic test", () -> {
                    assertTrue(13>2);
                    System.out.println(Thread.currentThread().getName()+" => 4th dynamic test");
                }),
                dynamicTest("5th dynamic test", () -> {
                    assertEquals(5, 25/5);
                    System.out.println(Thread.currentThread().getName()+" => 5th dynamic test");
                }),
                dynamicTest("6th dynamic test", () -> {
                    assertEquals(12,7 + 5);
                    System.out.println(Thread.currentThread().getName()+" => 6th dynamic test");
                })
        );

    }
}