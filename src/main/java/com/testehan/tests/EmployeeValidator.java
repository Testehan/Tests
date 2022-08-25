package com.testehan.tests;

import java.util.Arrays;

public class EmployeeValidator {

    private static final String[] ILLEGAL_NAME_CHARACTERS = { ",", "_", "{", "}", "!" };

    public static boolean validateName(Employee employee) throws ValidationException {
        boolean ret = true;
        // The validation rules go here.

        if (employee == null) {
            throw new ValidationException("Employee is null (not allowed)!");
        }
        if (employee.getName() == null) {
            throw new ValidationException("Employee name is null (not allowed)!");
        }
        if (employee.getSalary()<0 || employee.getSalary()==0) {
            throw new ValidationException("Employee salary is 0 or negative (not allowed)!");
        }
        if (!isNameValid(employee.getName())) {
            throw new ValidationException("Employee name (" + employee.getName() + ") may not contain any of the following characters: " + Arrays.toString(ILLEGAL_NAME_CHARACTERS) + "!");
        }
        return ret;
    }

    private static boolean isNameValid(String candidate) {
        boolean ret = true;
        for (String illegalChar : ILLEGAL_NAME_CHARACTERS) {
            if (candidate.contains(illegalChar)) {
                ret = false;
                break;
            }
        }
        return ret;
    }


    public static class ValidationException extends Exception {

        private static final long serialVersionUID = -134518049431883102L;

        // Probably should implement some more constructors, but don't want
        /// to tarnish the lesson...

        public ValidationException(String message) {
            super(message);

        }

    }
}
