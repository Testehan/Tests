package com.testehan.tests.parameterized;

import com.testehan.tests.Employee;

import java.util.ArrayList;
import java.util.List;

public class ClassThatJustHasAMethodThatWeNeed {
    static List<Employee> provideSomeEmployees(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("dan",1222));
        employees.add(new Employee("Caroline",1200));

        return employees;
    }
}
