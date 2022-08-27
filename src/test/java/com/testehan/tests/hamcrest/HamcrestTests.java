package com.testehan.tests.hamcrest;

import com.testehan.tests.Employee;
import org.junit.Test;

import java.util.*;

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
    public void collectionHamcrestExamples() {
        List<String> emptyList = new ArrayList<>();
        assertThat(emptyList, empty());

        List<Integer> list = Arrays.asList(5, 2, 4);

        assertThat(list, hasSize(3));

        // ensure the order is correct
        assertThat(list, contains(5, 2, 4));

        assertThat(list, containsInAnyOrder(2, 4, 5));

        String[] hamcrestMatchers = { "collections", "beans", "text", "number" };
        assertThat(hamcrestMatchers, hasItemInArray("text"));


        assertThat(list, everyItem(greaterThan(1)));
        assertThat(list, everyItem(notNullValue()));

        assertThat(list, allOf(hasSize(3),any(List.class),not(String.class),notNullValue()));

        Map<String, String> map = new HashMap<>();
        map.put("ABC", "DEF");
        assertThat(map, hasKey("ABC"));
        assertThat(map, hasValue("DEF"));
        assertThat(map, hasEntry("ABC", "DEF"));
    }

    @Test
    public void employee(){
        Employee e = new Employee();
        assertThat(e, hasProperty("name"));
    }


    // Number matchers
    @Test
    public void givenAnInteger_whenGreaterThan0_thenCorrect() {
        assertThat(1, greaterThan(0));
    }

    @Test
    public void givenAnInteger_whenGreaterThanOrEqTo5_thenCorrect() {
        assertThat(5, greaterThanOrEqualTo(5));
    }

    @Test
    public void givenAnInteger_whenLessThan0_thenCorrect() {
        assertThat(-1, lessThan(0));
    }

    @Test
    public void givenAnInteger_whenLessThanOrEqTo5_thenCorrect() {
        assertThat(-1, lessThanOrEqualTo(5));
    }

    @Test
    public void givenADouble_whenCloseTo_thenCorrect() {
        assertThat(1.2, closeTo(1, 0.5));
    }


    // String matchers
    @Test
    public void given2Strings_whenIsEqual_thenCorrect() {
        String str1 = "text";
        String str2 = "text";
        assertThat(str1, is(str2));
    }

    @Test
    public void givenString_whenEmpty_thenCorrect() {
        String str = "";
        assertThat(str, emptyString());
    }

    @Test
    public void givenString_whenEmptyOrNull_thenCorrect() {
        String str = null;
        assertThat(str, emptyOrNullString());
    }

    @Test
    public void given2Strings_whenEqualRegardlessWhiteSpace_thenCorrect() {
        String str1 = "text";
        String str2 = " text ";
        assertThat(str1, equalToCompressingWhiteSpace(str2));
    }

    @Test
    public void givenString_whenContainsGivenSubstring_thenCorrect() {
        String str = "calligraphy";
        assertThat(str, stringContainsInOrder(Arrays.asList("call", "graph")));
    }

    @Test
    public void given2Strings_whenEqual_thenCorrect() {
        String a = "foo";
        String b = "FOO";
        assertThat(a, equalToIgnoringCase(b));
    }

    @Test
    public void givenAString_whenStartsWithAnotherGivenString_thenCorrect() {
        String str1 = "calligraphy";
        String str2 = "call";
        assertThat(str1, startsWith(str2));
    }

    @Test
    public void givenAString_whenEndsWithAnotherGivenString_thenCorrect() {
        String str1 = "calligraphy";
        String str2 = "phy";
        assertThat(str1, endsWith(str2));
    }

    @Test
    public void givenAStrings_whenContainsAnotherGivenString_thenCorrect() {
        String str1 = "calligraphy";
        String str2 = "call";
        assertThat(str1, containsString(str2));
    }

    public void given2Strings_whenNotEqual_thenCorrect() {
        String str1 = "text";
        String str2 = "texts";
        assertThat(str1, not(str2));
    }
}
