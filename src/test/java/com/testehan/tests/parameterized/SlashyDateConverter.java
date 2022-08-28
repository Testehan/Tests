package com.testehan.tests.parameterized;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.time.LocalDate;

// custom converter that takes strings with the yyyy/mm/dd format and outputs LocalDate instances.
public class SlashyDateConverter implements ArgumentConverter {
    @Override
    public Object convert(Object source, ParameterContext parameterContext) throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException("The argument should be a string: " + source);
        }
        try {
            String[] parts = ((String) source).split("/");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert", e);
        }
    }
}
