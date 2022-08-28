package com.testehan.tests.extensions.parameterresolution;

import com.testehan.tests.Employee;
import com.testehan.tests.EmployeeValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/*
    @Nested – creates a nested test class, complete with its own test lifecycle, separate from its parent class
    By using @Nested classes, we’re able to test both valid and invalid data in the same test class, while at the
    same time keeping them completely sandboxed away from each other:
*/
@Tag("EmployeeRelatedTest")
public class EmployeeValidatorTest {

    @Nested
    @DisplayName("When using Valid data")
    @ExtendWith(ValidEmployeeParameterResolver.class)
    public class ValidData {

        @RepeatedTest(value = 10)
        @DisplayName("All first names are valid")
        public void validateFirstName(Employee employee) {
            try {
                assertTrue(EmployeeValidator.validateName(employee));
            } catch (EmployeeValidator.ValidationException e) {
                fail("Exception not expected: " + e.getLocalizedMessage());
            }
        }
    }

    @Nested
    @DisplayName("When using Invalid data")
    @ExtendWith(InvalidEmployeeParameterResolver.class)
    public class InvalidData {

        @RepeatedTest(value = 10)
        @DisplayName("All first names are invalid")
        public void validateFirstName(Employee employee) {
            assertThrows(
                    EmployeeValidator.ValidationException.class,
                    () -> EmployeeValidator.validateName(employee));
        }
    }
}
