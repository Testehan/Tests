package com.testehan.tests.hamcrest;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestTests {
    @Test
    public void testAssertThatHasItems() {
        assertThat(
                Arrays.asList("Java", "Kotlin", "Scala"),
                hasItems("Java", "Kotlin"));
    }
}
