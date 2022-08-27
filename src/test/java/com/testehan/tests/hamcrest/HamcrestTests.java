package com.testehan.tests.hamcrest;

import com.testehan.tests.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestTests {

    @Test
    public void demosInstanceOfTest() {
        assertThat(Long.valueOf(1), instanceOf(Long.class));
        // shortcut for instanceOf
        assertThat(Long.valueOf(1), isA(Long.class));

        assertThat("test", anyOf(is("testing"), containsString("est")));
    }

    @Test
    public void testAssertThatHasItems() {
        assertThat(
                Arrays.asList("Java", "Kotlin", "Scala"),
                hasItems("Java", "Kotlin"));
    }

    @Test
    public void listHamcrestExamples() {
        List<Integer> list = Arrays.asList(5, 2, 4);

        assertThat(list, hasSize(3));

        // ensure the order is correct
        assertThat(list, contains(5, 2, 4));

        assertThat(list, containsInAnyOrder(2, 4, 5));

        assertThat(list, everyItem(greaterThan(1)));
        assertThat(list, everyItem(notNullValue()));

        assertThat(list, allOf(hasSize(3),any(List.class),not(String.class),notNullValue()));
    }

    @Test
    public void employee(){
        Employee e = new Employee();
        assertThat(e, hasProperty("name"));
    }
}
