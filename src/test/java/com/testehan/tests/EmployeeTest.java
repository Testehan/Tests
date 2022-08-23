package com.testehan.tests;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("My Test class") // I think that it is a bug as to why this is not displayed in IntelliJ
public class EmployeeTest {

    private Employee employee;

    @BeforeAll
    public static void setup() {
        System.out.println("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    public void init() {
        System.out.println("@BeforeEach - executes before each test method in this class");
        this.employee = new Employee();
    }

    @AfterEach
    public void tearDown() {
        System.out.println("@AfterEach - executed after each test method.");
        this.employee = null;
    }

    @AfterAll
    public static void done() {
        System.out.println("@AfterAll - executed after all test methods.");
    }

    @DisplayName("A name for the test - this appears in IDEA instead of the test method name")
    @Test
    @Tag("EmployeeTest")
    public void givenSalaryIsZero_whenYearlySalaryIsCalculated_zeroIsReturned(){
        // given
        this.employee.setSalary(0);

        // when
        int yearlySalary = this.employee.calculateYearlySalary();

        // then
        assertEquals(  0,yearlySalary);
    }

    @Test
    @Tag("EmployeeTest")
    public void givenSalaryIsPositive_whenYearlySalaryIsCalculated_positiveTimes12IsReturned(){
        // given
        this.employee.setSalary(1000);

        // when
        int yearlySalary = this.employee.calculateYearlySalary();

        // then
        assertEquals(  12000,yearlySalary);
    }

    @Test
    void lambdaExpressions() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        assertTrue(numbers.stream()
                .mapToInt(Integer::intValue)
                .sum() > 5, () -> "Sum should be greater than 5");


        assertTrue(()-> 3>1, ()-> "3 is bigger than 1");
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

    // Assumptions are used to run tests only if certain conditions are met. This is typically used for external
    // conditions that are required for the test to run properly, but which aren't directly related to whatever
    // is being tested.
    @Test
    void trueAssumption() {
        assumeTrue(employee != null);
        assertEquals(5 + 2, 7);
    }

    @Test
    void falseAssumption() {
        assumeFalse(5 > 1); // because of this, test will appear as skipped.
        assertEquals(5 + 2, 7);
    }

    @Test
    void assumptionThat() {
        String someString = "Just a string !!!!";
        assumingThat(
                someString.equals("Just a string"),
                () -> {
                    System.out.println("This is not executed because assumption is false. No TestAbortedException is thrown");
                    assertEquals(2 + 2, 4);
                }
        );
    }

    // Exception Testing
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

    @Test
    @Disabled("This is the new ignore")
    public void testDisabled(){
        System.out.println("This will not be printed because test is disabled.");
    }

}
