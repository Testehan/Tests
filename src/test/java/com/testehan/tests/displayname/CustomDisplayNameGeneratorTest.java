package com.testehan.tests.displayname;

import org.junit.Test;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

// not working for some reason...idk why
@DisplayNameGeneration(CustomDisplayNameGeneratorTest.CustomDisplayNameGenerator.class)
public class CustomDisplayNameGeneratorTest {

    @Test
    public void test1(){

    }

    @Test
    public void test2(){

    }

    @Test
    public void test3(){

    }

    static class CustomDisplayNameGenerator extends DisplayNameGenerator.Standard {
        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return "This does not seem to work in intellij";
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return super.generateDisplayNameForNestedClass(nestedClass);
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            return testMethod.getName() + " ;)";
        }
    }
}
