package com.education.onlinecampus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    private final DateTimeFormatter inputFormatter;
    private final DateTimeFormatter outputFormatter;

    public LocalDateTimeFormatter(
            @Value("${datetime.input.pattern}") String inputPattern,
            @Value("${datetime.output.pattern}") String outputPattern
    ) {
        this.inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
        this.outputFormatter = DateTimeFormatter.ofPattern(outputPattern);
    }

    @Override
    public LocalDateTime parse(String str, Locale locale) {
        return LocalDateTime.parse(str, inputFormatter);
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return localDateTime.format(outputFormatter);
    }
}
