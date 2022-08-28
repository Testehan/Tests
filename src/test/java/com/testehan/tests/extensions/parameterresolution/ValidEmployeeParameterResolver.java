package com.testehan.tests.extensions.parameterresolution;

import com.testehan.tests.Employee;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Random;

public class ValidEmployeeParameterResolver implements ParameterResolver {

    public static Employee[] VALID_EMPLOYEES = {
            new Employee().setName("Dan").setSalary(100),
            new Employee().setName("Dan").setSalary(1),
            new Employee().setName("Anna").setSalary(Integer.MAX_VALUE),
            new Employee().setName("Anna Luana").setSalary(Integer.MAX_VALUE),
            new Employee().setName("Dan12").setSalary(1)

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
            ret = VALID_EMPLOYEES[new Random().nextInt(VALID_EMPLOYEES.length)];
        }
        return ret;
    }
}
