package com.education.onlinecampus.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final LocalDateFormatter localDateFormatter;
    private final LocalDateTimeFormatter localDateTimeFormatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(localDateFormatter);
        registry.addFormatter(localDateTimeFormatter);
    }
}
