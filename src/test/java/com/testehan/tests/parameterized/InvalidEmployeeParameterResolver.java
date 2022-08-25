package com.testehan.tests.parameterized;

import com.testehan.tests.Employee;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Random;

public class InvalidEmployeeParameterResolver implements ParameterResolver {

    public static Employee[] INVALID_EMPLOYEES = {
            new Employee().setName("Dan,").setSalary(100),
            new Employee().setName("Dan").setSalary(-1),
            new Employee().setName("Dan").setSalary(0),
            null,
            new Employee(),
            new Employee().setName("Anna{}").setSalary(Integer.MAX_VALUE),
            new Employee().setName("Anna_Luana").setSalary(Integer.MAX_VALUE),
            new Employee().setName("Dan12!").setSalary(1)

    };

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        boolean ret = false;
        if (parameterContext.getParameter().getType() == Employee.class) {
            ret = true;
        }
        return ret;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Object ret = null;
        if (parameterContext.getParameter().getType() == Employee.class) {
            ret = INVALID_EMPLOYEES[new Random().nextInt(INVALID_EMPLOYEES.length)];
        }
        return ret;
    }
}
