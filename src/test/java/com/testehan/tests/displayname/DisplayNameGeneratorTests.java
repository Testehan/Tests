package com.testehan.tests.displayname;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Tests where underscore is replaced with space")
public class DisplayNameGeneratorTests {

    @Test
    void test_spaces_ok() {
    }

    @Test
    void test_spaces_fail() {
    }
}
