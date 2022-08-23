package com.testehan.suites;

import com.testehan.tests.EmployeeTest;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses(EmployeeTest.class)
@IncludeTags("EmployeeTest")
@SuiteDisplayName("Unit tests related to employee")
public class AllEmployeeUnitTests {
}
