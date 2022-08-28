package com.testehan.tests.parameterized;

import com.testehan.tests.Employee;
import com.testehan.tests.EmployeeValidator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;
import org.junit.platform.commons.util.StringUtils;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

// https://www.baeldung.com/parameterized-tests-junit-5

@Tag("current")
public class ParameterizedTests {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        assertTrue(EmployeeValidator.isOdd(number));
    }

    @ParameterizedTest
    @NullSource
    void inputParameterIsGivenNullValueFromAnnotation(String input) {
        assertTrue(input == null);
    }

    @ParameterizedTest
    @EmptySource
    void isBlank_ShouldReturnTrueForEmptyStrings(String input) {
        assertTrue(StringUtils.isBlank(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void isBlank_ShouldReturnTrueForEmptyOrNullStrings(String input) {
        assertTrue(StringUtils.isBlank(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void isBlank_ShouldReturnTrueForAllTypesOfBlankStrings(String input) {
        assertTrue(StringUtils.isBlank(input));
    }

    @ParameterizedTest
    @EnumSource(Month.class) // passing all 12 values from ENUM
    void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
        int monthNumber = month.getValue();
        assertTrue(monthNumber >= 1 && monthNumber <= 12);
    }

    @ParameterizedTest     // passing just some values from ENUM
    @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void someMonths_Are30DaysLong(Month month) {
        final boolean isALeapYear = false;
        assertEquals(30, month.length(isALeapYear));
    }


    @ParameterizedTest
    @CsvSource({"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"}) // Pssing strings
    void someMonths_Are30DaysLongCsv(Month month) { // but here we get the Month enum, because JUnit has converters that
        final boolean isALeapYear = false;          // do this automatically
        assertEquals(30, month.length(isALeapYear));
    }

    // but sometimes we need a custom converter like in the next example:
    @ParameterizedTest
    @CsvSource({"2018/12/25,2018", "2019/02/11,2019"})
    void getYear_ShouldWorkAsExpected(@ConvertWith(SlashyDateConverter.class) LocalDate date, int expected) {
        assertEquals(expected, date.getYear());
    }



    @ParameterizedTest
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }


    /*
    The numLinesToSkip attribute represents the number of lines to skip when reading the CSV files. By default,
     @CsvFileSource does not skip any lines, but this feature is usually useful for skipping the header lines
     like we did here.
     */
    @ParameterizedTest
    @CsvFileSource(resources = "parameterizedData.csv", numLinesToSkip = 1)
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValueCSVFile(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }



    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected, String message) {
        assertEquals(expected, StringUtils.isBlank(input),message);
    }

    @ParameterizedTest
    @MethodSource("com.testehan.tests.parameterized.ClassThatJustHasAMethodThatWeNeed#provideSomeEmployees")
    void employeeNameShouldNotBeNull(Employee e) {
        assertNotNull(e.getName());
    }


    @ParameterizedTest
    @ArgumentsSource(BlankStringsArgumentsProvider.class)
    void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProvider(String input) {
        assertTrue(StringUtils.isBlank(input));
    }


    @ParameterizedTest
    @CsvSource({"Isaac, 1000", "Charles, 1350"})
    void fullName_ShouldGenerateTheExpectedFullName(ArgumentsAccessor argumentsAccessor) {
        String name = argumentsAccessor.getString(0);
        Integer salary = new Integer((String)argumentsAccessor.get(1));

        Employee person = new Employee(name, salary);
        assertEquals(name, person.getName());
    }

    @ParameterizedTest
    @CsvSource({"Isaac,1000", "Charles,2000"})
    void fullName_ShouldGenerateTheExpectedFullName(@AggregateWith(EmployeeAgregator.class) Employee employee) {
        // purpose of this is to showcase the @AggregateWith ;)
        assertEquals(employee.getName(), employee.getName());
    }


    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(null, true, "this should be true"),
                Arguments.of("", true, "this should be true"),
                Arguments.of("  ", true, "this should be true"),
                Arguments.of("not blank", false, "this should be false")
        );
    }


}
