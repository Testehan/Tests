package com.testehan.tests.asserts;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class VariousAssertTests {

    @Test
    void whenAssertingEquality_thenEqual() {
        float square = 2 * 2;
        float rectangle = 2 * 2;

        assertEquals(square, rectangle);
    }

    @Test
    public void whenAssertingArraysEquality_thenEqual() {
        char[] expected = { 'J', 'u', 'p', 'i', 't', 'e', 'r' };
        char[] actual = "Jupiter".toCharArray();

        assertArrayEquals(expected, actual, "Arrays should be equal");
    }

    @Test
    public void whenAssertingConditions_thenVerified() {
        assertTrue(5 > 4, "5 is greater the 4");
        assertTrue(null == null, "null is equal to null");
    }

    @Test
    public void givenBooleanSupplier_whenAssertingCondition_thenVerified() {
        BooleanSupplier condition = () -> 5 > 6;

        assertFalse(condition, "5 is not greater then 6");
    }

    @Test
    void whenAssertingNotNull_thenTrue() {
        Object dog = new Object();

        assertNotNull(dog, () -> "The dog should not be null");
    }

    @Test
    public void whenAssertingNull_thenTrue() {
        Object cat = null;

        assertNull(cat, () -> "The cat should be null");
    }

    @Test
    void whenAssertingSameObject_thenSuccessfull() {
        String language = "Java";
        Optional<String> optional = Optional.of(language);

        assertSame(language, optional.get());
    }

    @Test
    void whenAssertingDifferentObject_thenSuccessfull() {
        String language = "Java";
        String foreignLanguage = "Chinese";

        assertNotSame(language, foreignLanguage);
    }

    @Test
    public void whenFailingATest_thenFailed() {
        // Test not completed ...useful when a test is work in progress
//        fail("FAIL - test not completed");
    }


    @Test
    void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                () -> assertNotEquals(numbers[0], 1),
                () -> assertEquals(numbers[3], 3),
                () -> assertNotEquals(numbers[4], 1)
        );
    }

    @Test
    void givenTwoLists_whenAssertingIterables_thenEquals() {
        Iterable<String> al = new ArrayList<>(asList("Java", "Junit", "Test"));
        Iterable<String> ll = new LinkedList<>(asList("Java", "Junit", "Test"));

        assertIterableEquals(al, ll);
    }

    @Test
    void whenAssertingEqualityListOfStrings_thenEqual() {
        // as you can see...in the expected list, you can even have regular expressions
        List<String> expected = asList("Java", "\\d+", "JUnit");
        List<String> actual = asList("Java", "11", "JUnit");

        assertLinesMatch(expected, actual);
    }

    @Test
    void whenAssertingEquality_thenNotEqual() {
        Integer value = 5; // result of an algorithm

        assertNotEquals(0, value, "The result cannot be 0");
    }

    @Test
    void shouldThrowException() {
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Not supported");
        });
        // in case we also want to check the message
        assertEquals("Not supported", exception.getMessage());
    }


    @Test
    void assertThrowsException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> Integer.valueOf(str));
    }

}
