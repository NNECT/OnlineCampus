package com.education.onlinecampus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    private static final String datePattern =  "yyyy-MM-dd'T'HH:mm";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

    @Override
    public LocalDateTime parse(String str, Locale locale) {
        return LocalDateTime.parse(str, formatter);
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return localDateTime.format(formatter);
    }
}
