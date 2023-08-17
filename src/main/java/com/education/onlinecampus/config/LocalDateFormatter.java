package com.education.onlinecampus.config;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {
    private static final String datePattern = "yyyy-MM-dd";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

    @Override
    public LocalDate parse(String str, Locale locale) {
        return LocalDate.parse(str, formatter);
    }

    @Override
    public String print(LocalDate date, Locale locale) {
        return date.format(formatter);
    }
}
