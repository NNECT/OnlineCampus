package com.education.onlinecampus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class LocalDateFormatter implements Formatter<LocalDate> {
    private final DateTimeFormatter formatter;

    public LocalDateFormatter(@Value("${date.pattern}") String datePattern) {
        this.formatter = DateTimeFormatter.ofPattern(datePattern);
    }

    @Override
    public LocalDate parse(String str, Locale locale) {
        return LocalDate.parse(str, formatter);
    }

    @Override
    public String print(LocalDate date, Locale locale) {
        return date.format(formatter);
    }
}
