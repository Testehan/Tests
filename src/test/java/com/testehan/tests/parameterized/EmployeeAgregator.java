package com.testehan.tests.parameterized;

import com.testehan.tests.Employee;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class EmployeeAgregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
        return new Employee(accessor.getString(0), new Integer(accessor.getString(1)));
    }
}
