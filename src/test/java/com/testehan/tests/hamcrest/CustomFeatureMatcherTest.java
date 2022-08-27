package com.testehan.tests.hamcrest;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CustomFeatureMatcherTest {

    @Test
    public void fellowShipOfTheRingShouldContainer7() {
        assertThat("Gandalf", length(is(7)));
    }

    public static  Matcher<String> length(Matcher<? super Integer> matcher) {
        return new FeatureMatcher<String, Integer>(matcher, "a String of length that", "length") {
            @Override
            protected Integer featureValueOf(String actual) {
                return actual.length();
            }
        };
    }
}
